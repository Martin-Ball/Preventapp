package com.martin.preventapp.controller

import android.content.Context
import com.martin.preventapp.model.entities.ProfileModel
import com.martin.preventapp.model.entities.Response.ProfileResponse
import com.martin.preventapp.view.fragments.seller.profile.ProfileFragment

class ProfileController: ProfileInterface.Controller {

    @JvmField
    var view: ProfileInterface.View? = null
    @JvmField
    var context: Context? = null

    companion object {
        private var profileController: ProfileController? = null
        @JvmStatic
        val instance: ProfileController?
            get() {
                if (profileController == null) {
                    profileController = ProfileController()
                }
                return profileController
            }
    }

    override fun setView(view: ProfileInterface.View) {
        this.view = view
    }

    override fun setContext(context: Context) {
        this.context = context
    }

    override fun getUserInfo() {
        ProfileModel.instance!!.getUserInfo()
    }

    override fun showUserInfo(info: ProfileResponse) {
        ProfileFragment.instance!!.showUserInfo(info)
    }

    override fun createBackup() {
        ProfileModel.instance!!.createBackup()
    }

    override fun restoreBackup() {
        ProfileModel.instance!!.restoreBackup()
    }

    override fun showToast(text: String) {
        ProfileFragment.instance!!.showToast(text)
    }
}