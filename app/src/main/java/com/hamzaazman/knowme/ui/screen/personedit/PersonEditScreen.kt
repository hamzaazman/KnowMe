package com.hamzaazman.knowme.ui.screen.personedit

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersonEditScreen(
    personId: Int?,
    onSaved: () -> Unit,
    onBack: () -> Unit,
    viewModel: PersonEditViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(personId) {
        if (personId != null) viewModel.load(personId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Kişi Ekle")
                },
                navigationIcon = {
                    IconButton(onClick = {
                        onSaved()
                    }) {
                        Icon(Icons.Default.ArrowBack, null)
                    }
                },
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                viewModel.save()
                onSaved()
            }) {
                Icon(Icons.Default.Check, null)
            }
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxSize()
        ) {

            OutlinedTextField(
                value = state.firstName,
                onValueChange = viewModel::setFirstName,
                label = { Text("Ad") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(8.dp))

            OutlinedTextField(
                value = state.lastName,
                onValueChange = viewModel::setLastName,
                label = { Text("Soyad") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(8.dp))

            OutlinedTextField(
                value = state.notes,
                onValueChange = viewModel::setNotes,
                label = { Text("Notlar") },
                modifier = Modifier.height(140.dp).fillMaxWidth()

            )
        }
    }
}
