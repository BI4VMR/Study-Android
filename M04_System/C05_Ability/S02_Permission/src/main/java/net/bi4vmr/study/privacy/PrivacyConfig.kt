package net.bi4vmr.study.privacy

import java.lang.ref.WeakReference

/**
 * TODO 添加简述。
 *
 * TODO 添加详情。
 *
 * @author bi4vmr@outlook.com
 */
class PrivacyConfig constructor(
    private val uiExecutor: DelayableExecutor,
    private val deviceConfigProxy: DeviceConfigProxy
) {

    internal companion object {
        const val TAG = "PrivacyConfig"
        private const val MIC_CAMERA = SystemUiDeviceConfigFlags.PROPERTY_MIC_CAMERA_ENABLED
        private const val LOCATION = SystemUiDeviceConfigFlags.PROPERTY_LOCATION_INDICATORS_ENABLED
        private const val MEDIA_PROJECTION =
            SystemUiDeviceConfigFlags.PROPERTY_MEDIA_PROJECTION_INDICATORS_ENABLED
        private const val DEFAULT_MIC_CAMERA = true
        private const val DEFAULT_LOCATION = false
        private const val DEFAULT_MEDIA_PROJECTION = true
    }

    private val callbacks = mutableListOf<WeakReference<Callback>>()

    var micCameraAvailable = isMicCameraEnabled()
        private set
    var locationAvailable = isLocationEnabled()
        private set
    var mediaProjectionAvailable = isMediaProjectionEnabled()
        private set

    private val devicePropertiesChangedListener =
        DeviceConfig.OnPropertiesChangedListener { properties ->
            if (DeviceConfig.NAMESPACE_PRIVACY == properties.namespace) {
                // Running on the ui executor so can iterate on callbacks
                if (properties.keyset.contains(MIC_CAMERA)) {
                    micCameraAvailable = properties.getBoolean(MIC_CAMERA, DEFAULT_MIC_CAMERA)
                    callbacks.forEach { it.get()?.onFlagMicCameraChanged(micCameraAvailable) }
                }

                if (properties.keyset.contains(LOCATION)) {
                    locationAvailable = properties.getBoolean(LOCATION, DEFAULT_LOCATION)
                    callbacks.forEach { it.get()?.onFlagLocationChanged(locationAvailable) }
                }

                if (properties.keyset.contains(MEDIA_PROJECTION)) {
                    mediaProjectionAvailable =
                        properties.getBoolean(MEDIA_PROJECTION, DEFAULT_MEDIA_PROJECTION)
                    callbacks.forEach {
                        it.get()?.onFlagMediaProjectionChanged(mediaProjectionAvailable)
                    }
                }
            }
        }

    init {
        deviceConfigProxy.addOnPropertiesChangedListener(
            DeviceConfig.NAMESPACE_PRIVACY,
            uiExecutor,
            devicePropertiesChangedListener)
    }

    private fun isMicCameraEnabled(): Boolean {
        return deviceConfigProxy.getBoolean(DeviceConfig.NAMESPACE_PRIVACY,
            MIC_CAMERA, DEFAULT_MIC_CAMERA)
    }

    private fun isLocationEnabled(): Boolean {
        return deviceConfigProxy.getBoolean(DeviceConfig.NAMESPACE_PRIVACY,
            LOCATION, DEFAULT_LOCATION)
    }

    private fun isMediaProjectionEnabled(): Boolean {
        return deviceConfigProxy.getBoolean(DeviceConfig.NAMESPACE_PRIVACY,
            MEDIA_PROJECTION, DEFAULT_MEDIA_PROJECTION)
    }

    fun addCallback(callback: Callback) {
        addCallback(WeakReference(callback))
    }

    fun removeCallback(callback: Callback) {
        removeCallback(WeakReference(callback))
    }

    private fun addCallback(callback: WeakReference<Callback>) {
        uiExecutor.execute {
            callbacks.add(callback)
        }
    }

    private fun removeCallback(callback: WeakReference<Callback>) {
        uiExecutor.execute {
            // Removes also if the callback is null
            callbacks.removeIf { it.get()?.equals(callback.get()) ?: true }
        }
    }

    interface Callback {
        fun onFlagMicCameraChanged(flag: Boolean) {}

        fun onFlagLocationChanged(flag: Boolean) {}

        fun onFlagMediaProjectionChanged(flag: Boolean) {}
    }
}
