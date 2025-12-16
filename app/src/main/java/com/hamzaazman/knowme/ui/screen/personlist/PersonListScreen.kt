package com.hamzaazman.knowme.ui.screen.personlist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hamzaazman.knowme.domain.model.Person

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
            state.groupedPersons.forEach { (initial, persons) ->
                stickyHeader(key = initial) {
                    PersonHeaderItem(initial = initial)
                }
                items(
                    items = persons,
                    key = { it.id }
                ) { person ->
                    PersonListItem(
                        person = person,
                        onClick = { onPersonClick(person.id) }
                    )
                }
            }
        }
    }

}
@Composable
fun PersonHeaderItem(initial: Char) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = MaterialTheme.colorScheme.primaryContainer,
        shadowElevation = 2.dp
    ) {
        Text(
            text = initial.toString(),
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
    }
}

@Composable
fun PersonListItem(
    person: Person,
    onClick: () -> Unit
) {
    ListItem(
        headlineContent = {
            Text(text = "${person.firstName} ${person.lastName}")
        },
        leadingContent = {
            Icon(
                Icons.Default.Person,
                contentDescription = null,
            )
        },
        modifier = Modifier.clickable { onClick() }
    )
}