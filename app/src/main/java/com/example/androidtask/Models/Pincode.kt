package com.example.androidtask.Models

data class Pincode(
    val Message: String,
    val Status:String,
    val PostOffice:ArrayList<PostOffice>,
)
data class PostOffice(
    val Name:String,
    val Description: String? =null,
    val BranchType:String,
    val Circle:String,
    val District:String,
    val Division:String,
    val Region:String,
    val Block:String,
    val State:String,
    val Country:String,
    val Pincode:String
)

