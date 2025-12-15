package com.hamzaazman.knowme.ui.screen.personlist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersonListScreen(
    modifier: Modifier = Modifier,
    onPersonClick: (Int) -> Unit,
    onAddClick: () -> Unit,
    viewModel: PersonListViewModel = viewModel()
) {
    val state by viewModel.state.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Kişiler")
                }
            )
        },

        floatingActionButton = {
            FloatingActionButton(onClick = onAddClick) {
                Icon(Icons.Default.Add, null)
            }
        }
    ) { innerPadding ->

        LazyColumn(modifier = modifier.padding(innerPadding)) {
            items(state.persons) { person ->
                ListItem(
                    headlineContent = {
                        Text(text = "${person.firstName} ${person.lastName}")
                    },
                    leadingContent = {
                        Icon(
                            Icons.Default.Person,
                            null,
                        )
                    },
                    modifier = Modifier.clickable {
                        onPersonClick(person.id)
                    },
                )
            }
        }
    }

}