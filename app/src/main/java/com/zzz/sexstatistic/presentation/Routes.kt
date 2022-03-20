package com.zzz.sexstatistic.presentation

object Routes {
    const val SIGN_IN = "/sign_in"
    const val SIGN_UP = "/sign_up"
    const val MAIN = "/main"
    const val PROFILE = "/profile"
    const val INBOX = "/inbox"
    const val PARTNERS = "/partners"
    const val NEX_SEX = "/new_sex"

    fun getSexRoute(sexId: String): String {
        return "/sex/$sexId"
    }

    fun getSexEditRoute(sexId: String): String {
        return "/sex/$sexId/edit"
    }
}
