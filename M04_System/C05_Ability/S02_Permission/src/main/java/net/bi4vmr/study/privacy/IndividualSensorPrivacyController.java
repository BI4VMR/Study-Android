package net.bi4vmr.study.privacy;

public interface IndividualSensorPrivacyController extends
        CallbackController<IndividualSensorPrivacyController.Callback> {
    void init();

    boolean supportsSensorToggle(int sensor);

    boolean isSensorBlocked(int sensor);

    /**
     * Returns {@code true} if the given sensor is blocked by a hardware toggle, {@code false}
     * if the sensor is not blocked or blocked by a software toggle.
     */
    boolean isSensorBlockedByHardwareToggle(int sensor);

    void setSensorBlocked(int source, int sensor, boolean blocked);

    void suppressSensorPrivacyReminders(int sensor, boolean suppress);

    /**
     * @return whether lock screen authentication is required to change the toggle state
     */
    boolean requiresAuthentication();

    interface Callback {
        void onSensorBlockedChanged(int sensor, boolean blocked);
    }
}
