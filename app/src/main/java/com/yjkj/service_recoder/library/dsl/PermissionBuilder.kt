package com.yjkj.service_recoder.library.dsl

import pub.devrel.easypermissions.EasyPermissions

/**
 *@Created by houxiaoyao on 2022/5/9 1:27
 *  dsl简化easyPermissionCallback
 */

private typealias OnRequestPermissionsResultCallback = (
    requestCode: Int,
    permissions: Array<out String>,
    grantResults: IntArray,
) -> Unit

private typealias OnPermissionsGrantedCallback = (requestCode: Int, perms: MutableList<String>) -> Unit

private typealias OnPermissionsDeniedCallback = (requestCode: Int, perms: MutableList<String>) -> Unit

class PermissionBuilder : EasyPermissions.PermissionCallbacks{

    private var onPermissionsGrantedCallback : OnPermissionsGrantedCallback? = null
    private var onRequestPermissionsResultCallback : OnRequestPermissionsResultCallback? = null
    private var onPermissionsDeniedCallback : OnPermissionsDeniedCallback? = null

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray,
    ) {
        onRequestPermissionsResultCallback?.invoke(requestCode, permissions, grantResults) ?:Unit
    }


    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
        onPermissionsGrantedCallback?.invoke(requestCode, perms)?:Unit
        //"通过了==${perms.toString()}".log("PermissionResult")
    }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        onPermissionsDeniedCallback?.invoke(requestCode, perms)?:Unit
        //"有权限拒绝==${perms.toString()}".log("PermissionResult")
    }

    fun requestPermissionsResult(callback: OnRequestPermissionsResultCallback){
        onRequestPermissionsResultCallback = callback
    }

    fun permissionsGranted(callback: OnPermissionsGrantedCallback){
        onPermissionsGrantedCallback = callback
    }

    fun permissionsDenied(callback: OnPermissionsDeniedCallback){
        onPermissionsDeniedCallback = callback
    }
}

fun registerPermission(function: PermissionBuilder.()->Unit) = PermissionBuilder().also(function)




























