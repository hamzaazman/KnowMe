package com.hamzaazman.knowme.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.ui.NavDisplay
import com.hamzaazman.knowme.ui.screen.persondetail.PersonDetailScreen
import com.hamzaazman.knowme.ui.screen.personedit.PersonEditScreen
import com.hamzaazman.knowme.ui.screen.personlist.PersonListScreen


data object PersonListRoute
data class PersonDetailRoute(val id: Int)
data class PersonEditRoute(val id: Int? = null)


@Composable
fun AppNav() {

    val backStack = remember { mutableStateListOf<Any>(PersonListRoute) }

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = { route ->
            when (route) {

                is PersonListRoute -> NavEntry(route) {
                    PersonListScreen(
                        onPersonClick = { id ->
                            backStack.add(PersonDetailRoute(id))
                        },
                        onAddClick = {
                            backStack.add(PersonEditRoute())
                        },
                    )
                }

                is PersonDetailRoute -> NavEntry(route) {
                    PersonDetailScreen(
                        personId = route.id,
                        onEdit = {
                            backStack.add(PersonEditRoute(route.id))
                        },
                        onBack = {
                            backStack.removeLastOrNull()
                        },
                    )
                }

                is PersonEditRoute -> NavEntry(route) {
                    PersonEditScreen(
                        personId = route.id,
                        onSaved = {
                            backStack.removeLastOrNull()
                        },
                        onBack = {
                            backStack.removeLastOrNull()
                        },
                    )
                }

                else -> NavEntry(Unit) {
                    Text("Unknown Route")
                }
            }
        }
    )
}
