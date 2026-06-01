package com.pdmcourse2026.basictemplate.screens.vote

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pdmcourse2026.basictemplate.ui.screens.vote.VoteViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VoteScreen(
  navigateToResults: () -> Unit,
  viewModel: VoteViewModel = viewModel()
) {
  // Consumimos el StateFlow de forma segura
  val uiState by viewModel.uiState.collectAsState()

  Scaffold(
    topBar = {
      TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
          containerColor = MaterialTheme.colorScheme.primaryContainer,
          titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        title = { Text("RankeUCA - Vota") },
      )
    },
    bottomBar = {
      // El botón solo se habilita si isVoteSuccessful es true
      Button(
        onClick = navigateToResults,
        enabled = uiState.isVoteSuccessful,
        modifier = Modifier
          .fillMaxWidth()
          .padding(16.dp)
      ) {
        Text("Ir a resultados ->")
      }
    }
  ) { innerPadding ->
    Box(modifier = Modifier.padding(innerPadding).fillMaxSize()) {

      // 1. Manejo de Loading
      if (uiState.isLoading) {
        CircularProgressIndicator(modifier = Modifier.align(androidx.compose.ui.Alignment.Center))
      }

      // 2. Manejo de la lista
      if (uiState.places.isNotEmpty()) {
        // Aquí iría tu LazyColumn usando el componente PlaceCard
        // Al hacer click en un item llamas a: viewModel.voteForPlace(lugar.id)
      }

      // 3. Manejo de Errores (puedes usar un Snackbar o un Text temporal)
      uiState.errorMessage?.let { error ->
        Text(
          text = error,
          color = MaterialTheme.colorScheme.error,
          modifier = Modifier.align(androidx.compose.ui.Alignment.BottomCenter)
        )
      }
    }
  }
}