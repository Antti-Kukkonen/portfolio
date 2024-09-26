const greetingsDataset1 = {
    id: 'greetings-1',
    name: 'Japanska Hälsningar 1',
    questions: [
      {
        question: 'Vad betyder "hai"?',
        options: ["Ja", "Nej", "Kanske", "Hej"],
        correctAnswer: "Ja",
      },
      {
        question: 'Vad betyder "iie"?',
        options: ["Ja", "Nej", "Kanske", "Hej"],
        correctAnswer: "Nej",
      },
      {
        question: 'Vad betyder "konnichiwa"?',
        options: ["Ja", "Nej", "Kanske", "Hej"],
        correctAnswer: "Hej",
      },
      {
        question: 'Vad betyder "ja ne"?',
        options: ["Ja", "Nej", "Adjö", "Hej"],
        correctAnswer: "Adjö",
      },
      {
        question: 'Vad betyder "sumimasen"?',
        options: ["Ja", "Nej", "Ursäkta", "Hej"],
        correctAnswer: "Ursäkta",
      },
      {
        question: 'Vad betyder "arigatou"?',
        options: ["Ja", "Nej", "Ursäkta", "Tack"],
        correctAnswer: "Tack",
      },
      {
        question: 'Vad betyder "gome nasai"?',
        options: ["Ja", "Nej", "Ursäkta", "Förlåt"],
        correctAnswer: "Förlåt",
      },
    ],
  };
  
  const greetingsDataset2 = {
    id: 'greetings-2',
    name: 'Japanska Hälsningar 2',
    questions: [
      {
        question: 'Vad betyder "すみません"?',
        options: ["Ja", "Nej", "Ursäkta", "Hej"],
        correctAnswer: "Ursäkta",
      },
      {
        question: 'Vad betyder "ごめんなさい"?',
        options: ["Ja", "Nej", "Ursäkta", "Förlåt"],
        correctAnswer: "Förlåt",
      },
      {
        question: 'Vad betyder "ありがとう"?',
        options: ["Ja", "Nej", "Ursäkta", "Tack"],
        correctAnswer: "Tack",
      },
      {
        question: 'Vad betyder "はい"?',
        options: ["Ja", "Nej", "Kanske", "Hej"],
        correctAnswer: "Ja",
      },
      {
        question: 'Vad betyder "こんにちは"?',
        options: ["Ja", "Nej", "Kanske", "Hej"],
        correctAnswer: "Hej",
      },
      {
        question: 'Vad betyder "じゃね" på engelska?',
        options: ["Ja", "Nej", "Adjö", "Hej"],
        correctAnswer: "Adjö",
      },
      {
        question: 'Vad betyder "いいえ"?',
        options: ["Ja", "Nej", "Kanske", "Hej"],
        correctAnswer: "Nej",
      },
    ],
  };
  
  const pronounsDataset1 = {
    id: 'pronouns-1',
    name: 'Japanska Pronomen 1',
    questions: [
      {
        question: 'Vad betyder "watashi"?',
        options: ["Jag", "Du", "Han", "Hon"],
        correctAnswer: "Jag",
      },
      {
        question: 'Vad betyder "anata"?',
        options: ["Jag", "Du", "Han", "Hon"],
        correctAnswer: "Du",
      },
      {
        question: 'Vad betyder "kare"?',
        options: ["Jag", "Du", "Han", "Hon"],
        correctAnswer: "Han",
      },
      {
        question: 'Vad betyder "kanojo"?',
        options: ["Jag", "Du", "Han", "Hon"],
        correctAnswer: "Hon",
      },
      {
        question: 'Vad betyder "boku"?',
        options: ["Jag", "Du", "Han", "Hon"],
        correctAnswer: "Jag",
      },
      {
        question: 'Vad betyder "ore"?',
        options: ["Jag", "Du", "Han", "Hon"],
        correctAnswer: "Jag",
      },
      {
        question: 'Vad betyder "wareware"?',
        options: ["Jag", "Vi", "Han", "Hon"],
        correctAnswer: "Vi",
      },
      {
        question: 'Vad betyder "sore"?',
        options: ["Dem", "Det", "Han", "Hon"],
        correctAnswer: "Det",
      },
      {
        question: 'Vad betyder "karera"?',
        options: ["Dem", "De", "Han", "Honom"],
        correctAnswer: "De",
      },
      {
        question: 'Vad betyder "kore"?',
        options: ["Dem", "Detta", "Han", "Hon"],
        correctAnswer: "Detta",
      },
    ],
  };
  
  const pronounsDataset2 = {
    id: 'pronouns-2',
    name: 'Japanska Pronomen 2',
    questions: [
      {
        question: 'Vad betyder "かれ"?',
        options: ["Jag", "Du", "Han", "Hon"],
        correctAnswer: "Han",
      },
      {
        question: 'Vad betyder "それ"?',
        options: ["Dem", "Det", "Han", "Hon"],
        correctAnswer: "Det",
      },
      {
        question: 'Vad betyder "かのじょ"?',
        options: ["Jag", "Du", "Han", "Hon"],
        correctAnswer: "Hon",
      },
      {
        question: 'Vad betyder "ぼく"?',
        options: ["Jag", "Du", "Han", "Hon"],
        correctAnswer: "Jag",
      },
      {
        question: 'Vad betyder "おれ"?',
        options: ["Jag", "Du", "Han", "Hon"],
        correctAnswer: "Jag",
      },
      {
        question: 'Vad betyder "これ"?',
        options: ["Dem", "Detta", "Han", "Hon"],
        correctAnswer: "Detta",
      },
      {
        question: 'Vad betyder "あなた"?',
        options: ["Jag", "Du", "Han", "Hon"],
        correctAnswer: "Du",
      },
      {
        question: 'Vad betyder "わたし"?',
        options: ["Jag", "Du", "Han", "Hon"],
        correctAnswer: "Jag",
      },
      {
        question: 'Vad betyder "われわれ"?',
        options: ["Vi", "Du", "Han", "Hon"],
        correctAnswer: "Vi",
      },
      {
        question: 'Vad betyder "かれら"?',
        options: ["Dem", "De", "Han", "Honom"],
        correctAnswer: "De",
      },
    ],
  };
  
  const numbersDataset1 = {
    id: 'numbers-1',
    name: 'Japanska Nummer 1',
    questions: [
      {
        question: 'Vad betyder "ni"?',
        options: ["Ett", "Två", "Tre", "Fyra"],
        correctAnswer: "Två",
      },
      {
        question: 'Vad betyder "yon"?',
        options: ["Ett", "Två", "Tre", "Fyra"],
        correctAnswer: "Fyra",
      },
      {
        question: 'Vad betyder "hachi"?',
        options: ["Fem", "Sex", "Sju", "Åtta"],
        correctAnswer: "Åtta",
      },
      {
        question: 'Vad betyder "go"?',
        options: ["Fem", "Sex", "Sju", "Åtta"],
        correctAnswer: "Fem",
      },
      {
        question: 'Vad betyder "san"?',
        options: ["Ett", "Två", "Tre", "Fyra"],
        correctAnswer: "Tre",
      },
      {
        question: 'Vad betyder "juu"?',
        options: ["Nio", "Tio", "Elva", "Tolv"],
        correctAnswer: "Tio",
      },
      {
        question: 'Vad betyder "roku"?',
        options: ["Fem", "Sex", "Sju", "Åtta"],
        correctAnswer: "Sex",
      },
      {
        question: 'Vad betyder "ichi"?',
        options: ["Ett", "Två", "Tre", "Fyra"],
        correctAnswer: "Ett",
      },
      {
        question: 'Vad betyder "nana"?',
        options: ["Fem", "Sex", "Sju", "Åtta"],
        correctAnswer: "Sju",
      },
      {
        question: 'Vad betyder "kyuu"?',
        options: ["Nio", "Tio", "Elva", "Tolv"],
        correctAnswer: "Nio",
      },
    ],
  };
  
  const numbersDataset2 = {
    id: 'numbers-2',
    name: 'Japanska Nummer 2',
    questions: [
      {
        question: 'Vad betyder "じゅう"?',
        options: ["Nio", "Tio", "Elva", "Tolv"],
        correctAnswer: "Tio",
      },
      {
        question: 'Vad betyder "いち"?',
        options: ["Ett", "Två", "Tre", "Fyra"],
        correctAnswer: "Ett",
      },
      {
        question: 'Vad betyder "さん"?',
        options: ["Ett", "Två", "Tre", "Fyra"],
        correctAnswer: "Tre",
      },
      {
        question: 'Vad betyder "ご"?',
        options: ["Fem", "Sex", "Sju", "Åtta"],
        correctAnswer: "Fem",
      },
      {
        question: 'Vad betyder "ろく"?',
        options: ["Fem", "Sex", "Sju", "Åtta"],
        correctAnswer: "Sex",
      },
      {
        question: 'Vad betyder "よん"?',
        options: ["Ett", "Två", "Tre", "Fyra"],
        correctAnswer: "Fyra",
      },
      {
        question: 'Vad betyder "に"?',
        options: ["Ett", "Två", "Tre", "Fyra"],
        correctAnswer: "Två",
      },
      {
        question: 'Vad betyder "はち"?',
        options: ["Fem", "Sex", "Sju", "Åtta"],
        correctAnswer: "Åtta",
      },
      {
        question: 'Vad betyder "きゅう"?',
        options: ["Nio", "Tio", "Elva", "Tolv"],
        correctAnswer: "Nio",
      },
      {
        question: 'Vad betyder "しち"?',
        options: ["Fem", "Sex", "Sju", "Åtta"],
        correctAnswer: "Sju",
      },
    ],
  };
  
  export const allDataSets = {
    greetings: [greetingsDataset1, greetingsDataset2],
    pronouns: [pronounsDataset1, pronounsDataset2],
    numbers: [numbersDataset1, numbersDataset2],
  };
  