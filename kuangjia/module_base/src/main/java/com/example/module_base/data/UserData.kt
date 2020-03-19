package com.example.module_base.data

import java.io.Serializable

//user对象用到的地方很多 所以放到base
data class UserData(var id : Int,
                    var userId : String,
                    var userName : String,
                    var userPhone : String,
                    var cardId : String,
                    var loginPassword : String,
                    var dealPassword : String,
                    var rank : String,
                    var realName : String,
                    var nationality : String,
                    var referrer : String,
                    var lastLoginDate : String,
                    var email : String,
                    var openKey : String,
                    var userstatus : String ?) : Serializable

/*var id : Int,
var userId : String,
var userName : String,
var userPhone : String,
var cardId : String,
var loginPassword : String,
var dealPassword : String,
var rank : String,
var realName : String,
var nationality : String,
var referrer : String,
var lastLoginDate : String,
var email : String,
var openKey : String,
var raiseRank: Int,
var userstatus : String ?*/

