from machine import Pin, I2C, ADC, PWM
from fifo import Fifo
from piotimer import Piotimer
from math import sqrt
import ssd1306
import framebuf
import urequests as requests
import network


class TreeNode:
    def __init__(self, data):
        self.data = data
        self.data_dict = False
        self.data_values = None
        self.children = []
        self.parent = None

    def add_child(self, child):
        child.parent = self
        self.children.append(child)

    def insert_child(self, child):
        child.parent = self
        self.children.insert(0, child)


class RotaryMenu:
    def __init__(self):
        # Rotary
        self.dt_pin = Pin(10, Pin.IN)
        self.clk_pin = Pin(11, Pin.IN)
        self.clk_pin.irq(handler=self.rotary_change, trigger=Pin.IRQ_RISING, hard=True)
        self.rot_val = 0
        self.rot_sensitivity = 2

        # Button
        self.button = Pin(12, mode=Pin.IN, pull=Pin.PULL_UP)
        self.sw_value = False
        self.button.irq(handler=self.sw_press, trigger=Pin.IRQ_RISING, hard=True)

        # Menu tree
        self.root = TreeNode('main')
        self.mt_record_new = TreeNode('Record new')
        self.mt_start_recording = TreeNode('Start recording')
        self.mt_record_new.add_child(self.mt_start_recording)
        self.mt_record_new.add_child(TreeNode('<- Back'))

        self.mt_start_recording.add_child(TreeNode('<- Back'))

        self.mt_view_previous = TreeNode('View previous')
        self.mt_view_previous.add_child(TreeNode('<- Back'))

        self.root.add_child(self.mt_record_new)
        self.root.add_child(self.mt_view_previous)

        self.current_node = self.root

        self.recording = False
        self.new_draw = True
        self.displaying_values = False
        self.new_navigate = False
        self.i = 0
        self.old_i = 0
        # Display
        self.selected_display = 0
        # text offset
        self.container_width = 128
        self.container_height = 16
        self.x_offset = 2
        self.y_offset = int(self.container_height / 4)
        self.menu_offset = 0
        self.error = False
        self.remaining_time = 15

    def rotary_change(self, tid):
        self.new_draw = True
        if self.dt_pin.value() == 1:
            self.rot_val += 1
            if self.rot_val >= self.rot_sensitivity:
                self.rot_val = 0
                self.selected_display += 1
                if self.selected_display > len(self.current_node.children) - 1:
                    self.selected_display = 0
        elif self.dt_pin.value() == 0:
            self.rot_val -= 1
            if -self.rot_val >= self.rot_sensitivity:
                self.rot_val = 0
                self.selected_display -= 1
                if self.selected_display < 0:
                    self.selected_display = len(self.current_node.children) - 1
        self.menu_offset = ((self.selected_display) // 4) * 4

    def sw_press(self, tid):
        if self.i > self.old_i + 10:
            self.old_i = self.i
            self.recording = False
            self.new_navigate = True
            self.new_draw = True

    def draw_menu_boxes(self):
        for y in range(len(self.current_node.children)):
            fbuf.rect(0, self.container_height * (y - self.menu_offset), self.container_width, self.container_height, 1,
                      False)
            fbuf.rect(0 + 1, self.container_height * (y - self.menu_offset) + 1, self.container_width - 2,
                      self.container_height - 2, 0,
                      True)
            fbuf.text(self.current_node.children[y].data, 0 + self.x_offset,
                      self.container_height * (y - self.menu_offset) + self.y_offset,
                      1)

    def draw_highlighted_box(self):
        if len(self.current_node.children) > 0:
            fbuf.rect(0, self.container_height * (self.selected_display - self.menu_offset), self.container_width,
                      self.container_height, 0,
                      False)
            fbuf.rect(0 + 1, self.container_height * (self.selected_display - self.menu_offset) + 1,
                      self.container_width - 2,
                      self.container_height - 2, 1, True)
            fbuf.text(self.current_node.children[self.selected_display].data, 0 + self.x_offset,
                      self.container_height * (self.selected_display - self.menu_offset) + self.y_offset, 0)

    def draw_recording(self):
        display.fill(0)
        fbuf.fill(0)
        if self.remaining_time > 0:
            fbuf.text('Recording for:', int((128 - (14 * 8)) / 2), 26, 1)
            fbuf.text(f'{self.remaining_time}s', int((128 - (3 * 8)) / 2), 38, 1)
        else:
            fbuf.text('Analysing...', int((128 - (12 * 8)) / 2), 32, 1)

    def draw_error(self):
        display.fill(0)
        fbuf.fill(0)
        fbuf.text('Error 410:', int((128 - (10 * 8)) / 2), 26, 1)
        fbuf.text('dead user', int((128 - (9 * 8)) / 2), 38, 1)
        self.error = False

    def display_values(self):
        display.fill(0)
        fbuf.fill(0)
        if self.current_node.children[self.selected_display].data_values:
            for y in range(len(self.current_node.children[self.selected_display].data_values)):
                fbuf.text(self.current_node.children[self.selected_display].data_values[y], 0 + self.x_offset, 8 * y, 1)
        elif self.current_node.data_values:
            for y in range(len(self.current_node.data_values)):
                fbuf.text(self.current_node.data_values[y], 0 + self.x_offset, 8 * y, 1)

    def draw(self):
        display.fill(0)
        fbuf.fill(0)
        self.draw_menu_boxes()
        self.draw_highlighted_box()
        if self.recording:
            self.draw_recording()
        if self.displaying_values:
            self.display_values()
        if self.error:
            self.draw_error()
        display.blit(fbuf, 0, 0, 0)
        display.show()

    def record_new(self, time_seconds):
        start_time = self.i
        max_value = 0
        peak_no = 0
        new_peak_no = 0
        delta_sec = 0
        high = True
        PPI = []
        self.recording = True
        self.draw()

        while self.recording:
            valid_reading = False
            if not samples.empty():
                value = samples.get()
                rolling_average.put(value)
                rolling_average.get()
                threshold_high = int(sum(rolling_average.data) / len(rolling_average.data) * 1.08)
                threshold_low = int(sum(rolling_average.data) / len(rolling_average.data) * 1.02)
                if value > threshold_high and not high:  # going up
                    high = True

                if value < threshold_low and high:  # coming down
                    # Sample number distance * interval between samples = time between samples
                    delta_peak = (new_peak_no - peak_no)
                    if delta_peak < 0:
                        delta_peak += buffer_size
                    delta_sec = round(delta_peak * (1 / sample_rate) * 1000)

                    if 250 < delta_sec < 2000:
                        valid_reading = True

                    peak_no = new_peak_no
                    max_value = 0
                    high = False

                if high:
                    if value > max_value:
                        max_value = value
                        new_peak_no = samples.tail

            if valid_reading:
                PPI.append(delta_sec)
            elapsed_time = int((self.i - start_time) / 250)
            if self.i % 250 == 0:
                self.draw()
            self.remaining_time = time_seconds - elapsed_time
            if self.remaining_time <= 0:
                self.draw()
                self.recording = False

                data_set = {'type': 'RRI', 'data': PPI, 'analysis': {'type': 'readiness'}}
                response = requests.post(
                    url="https://analysis.kubioscloud.com/v2/analytics/analyze",
                    headers={"Authorization": "Bearer {}".format(access_token),
                             "X-Api-Key": APIKEY},
                    json=data_set)
                response = response.json()
                if response['status'] == 'ok':
                    time_stamp = response['analysis']['create_timestamp'][2:10] + '.' + response['analysis'][
                                                                                            'create_timestamp'][11:16]
                    new_data = TreeNode(time_stamp)
                    new_data.add_child(TreeNode('<- Back'))
                    new_data.data_dict = True
                    self.mt_view_previous.insert_child(new_data)
                    new_data.data_values = [f'HR: {response["analysis"]["mean_hr_bpm"]:.0f} bpm',
                                            f'RRI: {response["analysis"]["mean_rr_ms"]:.0f} ms',
                                            f'SDNN: {SDNN(PPI):.0f} ms',
                                            f'RMSSD: {RMSSD(PPI):.0f} ms',
                                            f'SNS: {response["analysis"]["sns_index"]:+.4f}',
                                            f'PNS: {response["analysis"]["pns_index"]:+.4f}']
                    self.current_node = self.mt_view_previous
                    self.selected_display = 0
                else:
                    self.error = True

    def navigate(self):
        if self.current_node.children[self.selected_display].data == 'Start recording':
            self.record_new(15)
        else:
            if self.current_node.children[self.selected_display].data == '<- Back':
                self.current_node = self.current_node.parent
                self.recording = False
                self.displaying_values = False
            elif self.current_node.children[self.selected_display].data_values is not None:
                self.displaying_values = True
                self.current_node = self.current_node.children[self.selected_display]
            elif self.current_node.children[self.selected_display].children:
                self.current_node = self.current_node.children[self.selected_display]
            else:
                pass
                # Should not be possible to get here
        self.selected_display = 0
        self.menu_offset = 0


class isr_fifo(Fifo):
    def __init__(self, size, adc_pin_nr):
        super().__init__(size)
        self.av = ADC(adc_pin_nr)
        self.dbg = Pin(0, Pin.OUT)  # debug GPIO pin for measuring timing with oscilloscope
        self.i = 0

    def handler(self, tid):
        if rotary.recording:
            self.put(self.av.read_u16())
        self.dbg.toggle()
        if self.i > 2 ** 31 - 1:
            self.i = 0  # resets every 99.4 days
        self.i += 1
        rotary.i += 1


def SDNN(intervals):
    RR = sum(intervals) / len(intervals)
    N = 0
    for n in intervals:
        N += (n - RR) ** 2
    return sqrt(1 / (len(intervals) - 1) * N)


def RMSSD(intervals):
    N = 0
    for n in range(len(intervals) - 1):
        N += (intervals[n + 1] - intervals[n]) ** 2
    return sqrt(1 / (len(intervals) - 1) * N)


# Indicator LEDS

LED_on = PWM(Pin(22, Pin.OUT))
LED_network = PWM(Pin(21, Pin.OUT))
LED_kubios = PWM(Pin(20, Pin.OUT))
LED_on.duty_u16(16 ** 2)
LED_network.duty_u16(0)
LED_kubios.duty_u16(0)

# network stuff
wlan = network.WLAN(network.STA_IF)
wlan.active(True)
wlan.connect('example_SSID', 'example_password')
if wlan.isconnected():
    LED_network.duty_u16(16 ** 2)
# kubios stuff
APIKEY = "example"
CLIENT_ID = "example"
CLIENT_SECRET = "example"
LOGIN_URL = "https://kubioscloud.auth.eu-west-1.amazoncognito.com/login"
TOKEN_URL = "https://kubioscloud.auth.eu-west-1.amazoncognito.com/oauth2/token"
response = requests.post(
    url=TOKEN_URL,
    data='grant_type=client_credentials&client_id={}'.format(CLIENT_ID),
    headers={'Content-Type': 'application/x-www-form-urlencoded'},
    auth=(CLIENT_ID, CLIENT_SECRET))
response = response.json()
access_token = response["access_token"]
LED_kubios.duty_u16(16 ** 2)

# init
fbuf = framebuf.FrameBuffer(bytearray(128 * 64 * 1), 128, 64, framebuf.MONO_VLSB)
rotary = RotaryMenu()
i2c = I2C(1, sda=Pin(14), scl=Pin(15), freq=400000)
display = ssd1306.SSD1306_I2C(128, 64, i2c)
display.show()

sample_rate = 250
buffer_size = 750
hr_fifo_size = 3

samples = isr_fifo(buffer_size, 26)
rolling_average = Fifo(sample_rate * 2)
heart_rates = Fifo(hr_fifo_size)
tmr = Piotimer(mode=Piotimer.PERIODIC, freq=sample_rate, callback=samples.handler)

while True:
    if rotary.new_navigate:
        rotary.navigate()
        rotary.new_navigate = False
        rotary.new_draw = True
    if rotary.new_draw:
        rotary.draw()
        rotary.new_draw = False
