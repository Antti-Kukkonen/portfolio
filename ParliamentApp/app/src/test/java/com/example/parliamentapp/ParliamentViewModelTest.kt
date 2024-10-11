package com.example.parliamentapp

import com.example.parliamentapp.fake.FakeDataSource
import com.example.parliamentapp.fake.FakeDefaultParliamentRepository
import com.example.parliamentapp.fake.FakeParliamentApiService
import com.example.parliamentapp.rules.TestDispatcherRule
import com.example.parliamentapp.viewmodel.ParliamentViewModel
import com.example.parliamentapp.viewmodel.UiState
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.Rule

/**
 * 2024/10/11, Antti Kukkonen, 2215598
 *
 * Test class for the ParliamentViewModel.
 * Tests the getParliamentMembers, getParliamentMemberExtras and getParliamentMemberNotes functions.
 * Tests the UiState for successes and errors.
 */
class ParliamentViewModelTest {

    @get:Rule
    val testDispatcherRule = TestDispatcherRule()

    @Test
    fun parliamentViewModel_getParliamentMembers_verifyUiStateSuccess() =
        runTest {
            val viewModel = ParliamentViewModel(
                FakeDefaultParliamentRepository(
                    FakeParliamentApiService()
                )
            )

            assertEquals(
                UiState.Success(FakeDataSource.fakeParliamentMembers),
                viewModel.members.value
            )
        }

    @Test
    fun parliamentViewModel_getParliamentMembers_verifyUiStateError() =
        runTest {
            val viewModel = ParliamentViewModel(
                FakeDefaultParliamentRepository(
                    FakeParliamentApiService(),
                    shouldReturnError = true
                )
            )

            assertEquals(
                UiState.Error("Failed to fetch members: Fake exception"),
                viewModel.members.value
            )
        }

    @Test
    fun parliamentViewModel_getParliamentMemberExtras_verifyUiStateSuccess() =
        runTest {
            val viewModel = ParliamentViewModel(
                FakeDefaultParliamentRepository(
                    FakeParliamentApiService()
                )
            )

            assertEquals(
                UiState.Success(FakeDataSource.fakeParliamentMemberExtras),
                viewModel.extras.value
            )
        }

    @Test
    fun parliamentViewModel_getParliamentMemberExtras_verifyUiStateError() =
        runTest {
            val viewModel = ParliamentViewModel(
                FakeDefaultParliamentRepository(
                    FakeParliamentApiService(),
                    shouldReturnError = true
                )
            )

            assertEquals(
                UiState.Error("Failed to fetch members' extra information: Fake exception"),
                viewModel.extras.value
            )
        }

    @Test
    fun parliamentViewModel_getParliamentMemberNotes_verifyUiStateSuccess() =
        runTest {
            val viewModel = ParliamentViewModel(
                FakeDefaultParliamentRepository(
                    FakeParliamentApiService()
                )
            )

            assertEquals(
                UiState.Success(FakeDataSource.fakeParliamentMemberNotes),
                viewModel.notes.value
            )
        }

    @Test
    fun parliamentViewModel_getParliamentMemberNotes_verifyUiStateError() =
        runTest {
            val viewModel = ParliamentViewModel(
                FakeDefaultParliamentRepository(
                    FakeParliamentApiService(),
                    shouldReturnError = true
                )
            )

            assertEquals(
                UiState.Error("Fake exception"),
                viewModel.notes.value
            )
        }

}