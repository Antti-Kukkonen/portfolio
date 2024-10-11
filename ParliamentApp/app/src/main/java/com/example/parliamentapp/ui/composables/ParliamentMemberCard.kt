package com.example.parliamentapp.ui.composables

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.twotone.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SheetState
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.example.parliamentapp.R
import com.example.parliamentapp.model.ParliamentMember
import com.example.parliamentapp.model.ParliamentMemberExtra
import com.example.parliamentapp.model.ParliamentMemberNote
import com.example.parliamentapp.viewmodel.ParliamentViewModel
import com.example.parliamentapp.viewmodel.UiState

const val IMAGE_URL_PREFIX = "https://avoindata.eduskunta.fi/"

/**
 * 2024/10/11, Antti Kukkonen, 2215598
 *
 * Composable for displaying a parliament member card.
 * Contains information about the member and a modal bottom sheet for adding notes.
 * The card is clickable to open the note modal.
 */
@Composable
fun ParliamentMemberCard(
    modifier: Modifier = Modifier,
    member: ParliamentMember?,
    extra: ParliamentMemberExtra?,
    viewModel: ParliamentViewModel
) {
    var noteMenuIsOpen by remember { mutableStateOf(false) }

    Card(
        modifier = modifier.padding(16.dp),
        onClick = {
            noteMenuIsOpen = true
        }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            member?.let { member ->
                MemberMainDisplay(member)
                MemberDetailDisplay(member)
                extra?.let { extra ->
                    MemberExtraDisplay(extra)
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Modal bottom sheet for adding a note to a parliament member
                NoteMenu(
                    member = member,
                    viewModel = viewModel,
                    isOpen = noteMenuIsOpen,
                    setIsOpen = { noteMenuIsOpen = it }
                )
            }
        }
    }
}

/**
 * 2024/10/11, Antti Kukkonen, 2215598
 *
 * Composable for displaying the main information of a parliament member.
 * Contains the image, party indicator and full name.
 */
@Composable
fun MemberMainDisplay(member: ParliamentMember) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Box {
            SubcomposeAsyncImage(
                model = "$IMAGE_URL_PREFIX${member.pictureUrl}",
                contentDescription = stringResource(R.string.cd_parliament_member_image),
                loading = {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .fillMaxWidth(0.6f)
                            .fillMaxHeight(0.5f)
                            .padding(16.dp)
                    )
                },
                error = {
                    Image(
                        painter = painterResource(id = R.drawable.baseline_image_not_supported_24),
                        contentDescription = stringResource(R.string.cd_default_member_image),
                        modifier = Modifier.fillMaxSize()
                    )
                },
            )
            PartyIndicator(
                party = member.party,
                Modifier
                    .align(Alignment.TopEnd)
                    .padding(8.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = stringResource(R.string.full_name, member.firstname, member.lastname),
            style = MaterialTheme.typography.titleMedium,
            maxLines = 2,
            modifier = Modifier
                .widthIn(max = 160.dp),
            textAlign = TextAlign.Center
        )
    }
}

/**
 * 2024/10/11, Antti Kukkonen, 2215598
 *
 * Composable for displaying details of a parliament member.
 * Contains the seat number, Heteka ID and minister status.
 */
@Composable
fun MemberDetailDisplay(member: ParliamentMember) {
    Text(
        text = stringResource(R.string.seat, member.seatNumber),
        style = MaterialTheme.typography.bodySmall
    )
    Text(
        text = stringResource(R.string.heteka_id, member.hetekaId),
        style = MaterialTheme.typography.bodySmall
    )
    Text(
        text = if (member.minister) stringResource(R.string.a_minister) else stringResource(R.string.not_a_minister),
        style = MaterialTheme.typography.bodySmall
    )
}

/**
 * 2024/10/11, Antti Kukkonen, 2215598
 *
 * Composable for displaying extra information of a parliament member.
 * Contains the constituency, birth year and a Twitter link if available.
 */
@Composable
fun MemberExtraDisplay(extra: ParliamentMemberExtra) {
    Text(
        text = stringResource(R.string.constituency, extra.constituency),
        style = MaterialTheme.typography.bodySmall
    )
    Text(
        text = stringResource(R.string.born, extra.bornYear),
        style = MaterialTheme.typography.bodySmall
    )
    if (extra.twitter != "") {
        TwitterLink(
            twitter = extra.twitter
        )
    }
}


/**
 * 2024/10/11, Antti Kukkonen, 2215598
 *
 * Composable for the note menu of a parliament member.
 * Contains a modal bottom sheet for adding a note to a parliament member.
 * The note is submitted when the modal is closed.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteMenu(
    member: ParliamentMember,
    viewModel: ParliamentViewModel,
    isOpen: Boolean = false,
    setIsOpen: (Boolean) -> Unit
) {
    val notesState by viewModel.notes.collectAsState()
    val note = (notesState as? UiState.Success)?.data?.find { it.hetekaId == member.hetekaId }

    var rating by remember { mutableIntStateOf(note?.rating ?: 0) }
    var comment by remember { mutableStateOf(note?.comment ?: "") }

    val sheetState = rememberModalBottomSheetState()

    // Submit the note with updated values
    fun submitNote() {
        val newNote = ParliamentMemberNote(
            hetekaId = member.hetekaId,
            rating = rating,
            comment = comment
        )
        viewModel.insertNote(newNote)
    }

    // Modal Bottom Sheet for adding a note
    if (isOpen) {
        NoteModalBottomSheet(
            sheetState = sheetState,
            notesState = notesState,
            member = member,
            rating = rating,
            comment = comment,
            onRatingChange = { rating = it },
            onCommentChange = { comment = it },
            onClose = {
                submitNote()
                setIsOpen(false)
            },
            onDelete = {
                viewModel.deleteNote(member.hetekaId)
            }
        )
    }
}

/**
 * 2024/10/11, Antti Kukkonen, 2215598
 *
 * Composable for the modal bottom sheet for adding a note to a parliament member.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteModalBottomSheet(
    sheetState: SheetState,
    notesState: UiState<List<ParliamentMemberNote>>,
    member: ParliamentMember,
    rating: Int,
    comment: String,
    onRatingChange: (Int) -> Unit,
    onCommentChange: (String) -> Unit,
    onClose: () -> Unit,
    onDelete: () -> Unit
) {
    ModalBottomSheet(
        onDismissRequest = onClose,
        sheetState = sheetState
    ) {
        when (notesState) {
            is UiState.Loading -> LoadingContent()
            is UiState.Error -> ErrorContent(notesState.message)
            is UiState.Success -> {
                NoteForm(
                    member = member,
                    rating = rating,
                    comment = comment,
                    onRatingChange = onRatingChange,
                    onCommentChange = onCommentChange,
                    onClose = onClose,
                    onDelete = onDelete
                )
            }
        }
    }
}

@Composable
fun LoadingContent() {
    CircularProgressIndicator()
}

@Composable
fun ErrorContent(errorMessage: String) {
    Text(stringResource(R.string.failed_to_fetch_notes, errorMessage))
}

/**
 * 2024/10/11, Antti Kukkonen, 2215598
 *
 * Composable for the note form of a parliament member.
 * Contains a rating row and a text field for adding a comment.
 * Also contains a delete button for deleting the note.
 */
@Composable
fun NoteForm(
    member: ParliamentMember,
    rating: Int,
    comment: String,
    onRatingChange: (Int) -> Unit,
    onCommentChange: (String) -> Unit,
    onClose: () -> Unit,
    onDelete: () -> Unit
) {
    Box {
        SmallFloatingActionButton(
            onClick = {
                onClose()
                onDelete()
            },
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(16.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = stringResource(R.string.delete_note)
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 28.dp, end = 16.dp, bottom = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Notes for ${member.firstname} ${member.lastname}",
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(Modifier.size(8.dp))

            RatingRow(rating = rating, onRatingChange = onRatingChange)

            Spacer(Modifier.size(16.dp))

            OutlinedTextField(
                value = comment,
                onValueChange = onCommentChange,
                label = { Text(stringResource(R.string.add_a_comment)) },
                modifier = Modifier.fillMaxWidth(),
                keyboardActions = KeyboardActions(
                    onDone = {
                        onClose()
                    }
                ),
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                )
            )

            Spacer(modifier = Modifier.size(32.dp))
        }
    }
}

/**
 * 2024/10/11, Antti Kukkonen, 2215598
 *
 * Composable for a row of rating stars.
 * Contains 5 stars that can be clicked to change the rating.
 */
@Composable
fun RatingRow(
    rating: Int,
    onRatingChange: (Int) -> Unit
) {
    Row(
        modifier = Modifier.padding(8.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        for (i in 1..5) {
            Icon(
                imageVector = if (i <= rating) Icons.Filled.Star else Icons.TwoTone.Star,
                contentDescription = stringResource(R.string.cd_star, i),
                tint = if (i <= rating) MaterialTheme.colorScheme.inverseSurface else MaterialTheme.colorScheme.primaryContainer,
                modifier = Modifier
                    .size(64.dp)
                    .clickable { onRatingChange(i) }
            )
        }
    }
}

/**
 * 2024/10/11, Antti Kukkonen, 2215598
 *
 * Composable for a Twitter link.
 * Contains a text button that opens the Twitter profile of the parliament member.
 */
@Composable
fun TwitterLink(
    modifier: Modifier = Modifier,
    twitter: String
) {
    val context = LocalContext.current

    TextButton(
        onClick = {
            val intent = Intent(Intent.ACTION_VIEW, android.net.Uri.parse(twitter))
            context.startActivity(intent)
        },
        modifier = modifier
    ) {
        Text(
            text = stringResource(R.string.twitter),
            style = MaterialTheme.typography.bodySmall,
            textDecoration = TextDecoration.Underline
        )
    }
}

