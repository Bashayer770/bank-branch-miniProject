package com.example.bankbranch

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bankbranch.compos.BranchCard
import com.example.bankbranch.compos.BranchDetailsScreen
import com.example.bankbranch.data.Branch
import com.example.bankbranch.data.BranchType
import com.example.bankbranch.ui.theme.BankBranchTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BankBranchTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BankBranchApp()
                }
            }
        }
    }
}

@Composable
fun BankBranchApp() {
    val navController = rememberNavController()
    val selectedBranch = remember { mutableStateOf<Branch?>(null) }
    val branches = listOf(
        Branch(
            id = 1,
            name = "Downtown Financial Center",
            type = BranchType.MAIN_BRANCH,
            address = "123 Financial District, New York, NY 10004",
            phone = "+1 (212) 555-0100",
            hours = "Mon-Fri: 9:00 AM - 5:00 PM\nSat: 10:00 AM - 2:00 PM",
            location = "https://maps.google.com/...",
            imageUri = "https://images.unsplash.com/photo-1560518883-ce09059eeffa?ixlib=rb-4.0.3&auto=format&fit=crop&w=1073&q=80"
        ),
        Branch(
            id = 2,
            name = "Westside ATM Center",
            type = BranchType.ATM,
            address = "456 West 57th Street, New York, NY 10019",
            phone = "24/7 ATM Service",
            hours = "24/7",
            location = "https://maps.google.com/...",
            imageUri = "https://images.unsplash.com/photo-1601597111158-2fceff292cdc?ixlib=rb-4.0.3&auto=format&fit=crop&w=1170&q=80"
        ),
        Branch(
            id = 3,
            name = "Midtown Branch",
            type = BranchType.SUB_BRANCH,
            address = "789 5th Avenue, New York, NY 10065",
            phone = "+1 (212) 555-0200",
            hours = "Mon-Fri: 8:30 AM - 6:00 PM\nSat: 9:00 AM - 3:00 PM",
            location = "https://maps.google.com/...",
            imageUri = "https://images.unsplash.com/photo-1604328698692-f76ea9498e76?ixlib=rb-4.0.3&auto=format&fit=crop&w=1170&q=80"
        ),
        Branch(
            id = 4,
            name = "Cash Deposit Center",
            type = BranchType.CASH_DEPOSIT,
            address = "321 Park Avenue, New York, NY 10022",
            phone = "+1 (212) 555-0300",
            hours = "Mon-Sun: 7:00 AM - 10:00 PM",
            location = "https://maps.google.com/...",
            imageUri = "https://images.unsplash.com/photo-1560518883-ce09059eeffa?ixlib=rb-4.0.3&auto=format&fit=crop&w=1073&q=80"
        )
    )
// NAV SETUP
    NavHost(
        navController = navController,
        startDestination = "branchList"
    ) {
        composable("branchList") {
            BranchListScreen(
                branches = branches,
                onBranchClick = { branch ->
                    selectedBranch.value = branch
                    navController.navigate("branchDetails")
                }
            )
        }
        composable("branchDetails") {
            val branch = selectedBranch.value
            if (branch != null) {
                BranchDetailsScreen(
                    branch = branch,
                    onBackClick = { navController.popBackStack() }
                )
            }
        }
    }
}

@Composable
fun BranchListScreen(
    branches: List<Branch>,
    onBranchClick: (Branch) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(branches) { branch ->
            BranchCard(
                branch = branch,
                modifier = Modifier.clickable { onBranchClick(branch) }
            )
        }
    }
} 