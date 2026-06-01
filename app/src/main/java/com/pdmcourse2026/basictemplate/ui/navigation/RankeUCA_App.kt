package com.pdmcourse2026.basictemplate

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.pdmcourse2026.basictemplate.screens.vote.VoteScreen
import com.pdmcourse2026.basictemplate.ui.navigation.Routes

@Composable
fun RankeUCA_App() {
  // Ahora arrancamos en la ruta Vote
  val backStack = rememberNavBackStack(Routes.Vote)

  NavDisplay(
    backStack = backStack,
    onBack = { backStack.removeLastOrNull() },
    entryProvider = entryProvider {

      // Conectamos la ruta Vote con tu VoteScreen
      entry<Routes.Vote> {
        VoteScreen(
          navigateToResults = { backStack.add(Routes.Results) }
        )
      }

      // Aquí irá la pantalla de resultados más adelante
      entry<Routes.Results> {
        // ResultsScreen(...)
      }
    },
  )
}