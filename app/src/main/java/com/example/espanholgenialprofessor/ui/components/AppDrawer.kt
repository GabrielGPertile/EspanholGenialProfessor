package com.example.espanholgenialprofessor.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.espanholgenialprofessor.domain.user.UserProfile
import com.example.espanholgenialprofessor.domain.user.UserRole

@Composable
fun AppDrawer(
    userProfile: UserProfile,
    onProfileClick: () -> Unit,
    onLogoutClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = Icons.Default.AccountCircle,
            modifier = Modifier.size(120.dp),
            contentDescription = "Foto de Perfil"
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = userProfile.name ?: "Usuário"
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = when (userProfile.role) {
                UserRole.TEACHER -> "Professor"
                UserRole.STUDENT -> "Aluno"
            }
        )

        Spacer(modifier = Modifier.height(8.dp))

        NavigationDrawerItem(
            label = {
                Text("Meu Perfil")
            },
            icon = {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = "Meu Perfil"
                )
            },
            selected = false,
            onClick = onProfileClick
        )

        Spacer(modifier = Modifier.weight(1f))

        NavigationDrawerItem(
            label = {
                Text("Sair")
            },
            icon = {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.Logout,
                    contentDescription = "Sair"
                )
            },
            selected = false,
            onClick = onLogoutClick
        )
    }
}