package com.example.espanholgenialprofessor.ui.screens.student

import android.R.attr.onClick
import android.widget.Button
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.espanholgenialprofessor.domain.user.UserProfile
import com.example.espanholgenialprofessor.navigation.Routes
import com.example.espanholgenialprofessor.ui.components.AppDrawer
import com.example.espanholgenialprofessor.ui.screens.teacher.HomeTeacherViewModel
import com.example.espanholgenialprofessor.ui.theme.Cream
import com.example.espanholgenialprofessor.ui.theme.WineRed
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeStudentScreen(
    navController: NavController,
    userProfile: UserProfile,
    viewModel: HomeTeacherViewModel = hiltViewModel()
)
{
    val drawerState = rememberDrawerState(
        initialValue = DrawerValue.Closed
    )

    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                modifier = Modifier.fillMaxWidth(0.73f),
                drawerContainerColor = Cream
            ) {
                AppDrawer(
                    userProfile = userProfile,
                    onProfileClick = {

                    },
                    onLogoutClick = {

                    }
                )
            }
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = WineRed
                ),
                navigationIcon = {
                    IconButton(
                        onClick = {
                            scope.launch {
                                drawerState.open()
                            }
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = "Abrir Menu"
                        )
                    }
                },
                title = {
                    Text(
                        "Menu",
                        color = Cream
                    )
                }
            )

            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text("Tela do Aluno")

                Button(
                    onClick = {
                        viewModel.logout()

                        navController.navigate(Routes.LOGIN) {
                            popUpTo(0)
                        }
                    }
                ) {
                    Text("Sair")
                }
            }
        }
    }
}