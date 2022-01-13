package uz.hamroev.toshkentshaharxotirakitob.model

class MyUser {
    var username: String? = null
    var profession: String? = null
    var profile_image: Int? = null

    constructor(username: String?, profession: String?, profile_image: Int?) {
        this.username = username
        this.profession = profession
        this.profile_image = profile_image
    }
}