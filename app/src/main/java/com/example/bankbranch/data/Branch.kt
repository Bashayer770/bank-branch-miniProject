package com.example.bankbranch.data

enum class BranchType(val displayName: String) {
    MAIN_BRANCH("Main Branch"),
    SUB_BRANCH("Sub Branch"),
    ATM("ATM"),
    CASH_DEPOSIT("Cash Deposit")
}

data class Branch(
    val id: Int,
    val name: String,
    val type: BranchType,
    val address: String,
    val phone: String,
    val hours: String,
    val location: String,
    val imageUri: String? = null
)


