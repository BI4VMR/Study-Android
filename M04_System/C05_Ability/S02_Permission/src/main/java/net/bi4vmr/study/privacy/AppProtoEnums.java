package net.bi4vmr.study.privacy;

public final class AppProtoEnums {

    // enum AppTransitionReasonEnum
    public static final int APP_TRANSITION_REASON_UNKNOWN = 0;
    public static final int APP_TRANSITION_SPLASH_SCREEN = 1;
    public static final int APP_TRANSITION_WINDOWS_DRAWN = 2;
    public static final int APP_TRANSITION_TIMEOUT = 3;
    public static final int APP_TRANSITION_SNAPSHOT = 4;
    public static final int APP_TRANSITION_RECENTS_ANIM = 5;

    // enum ProcessStateEnum
    public static final int PROCESS_STATE_UNKNOWN_TO_PROTO = 998;
    public static final int PROCESS_STATE_UNKNOWN = 999;
    public static final int PROCESS_STATE_PERSISTENT = 1000;
    public static final int PROCESS_STATE_PERSISTENT_UI = 1001;
    public static final int PROCESS_STATE_TOP = 1002;
    public static final int PROCESS_STATE_BOUND_TOP = 1020;
    public static final int PROCESS_STATE_FOREGROUND_SERVICE = 1003;
    public static final int PROCESS_STATE_BOUND_FOREGROUND_SERVICE = 1004;
    public static final int PROCESS_STATE_IMPORTANT_FOREGROUND = 1005;
    public static final int PROCESS_STATE_IMPORTANT_BACKGROUND = 1006;
    public static final int PROCESS_STATE_TRANSIENT_BACKGROUND = 1007;
    public static final int PROCESS_STATE_BACKUP = 1008;
    public static final int PROCESS_STATE_SERVICE = 1009;
    public static final int PROCESS_STATE_RECEIVER = 1010;
    public static final int PROCESS_STATE_TOP_SLEEPING = 1011;
    public static final int PROCESS_STATE_HEAVY_WEIGHT = 1012;
    public static final int PROCESS_STATE_HOME = 1013;
    public static final int PROCESS_STATE_LAST_ACTIVITY = 1014;
    public static final int PROCESS_STATE_CACHED_ACTIVITY = 1015;
    public static final int PROCESS_STATE_CACHED_ACTIVITY_CLIENT = 1016;
    public static final int PROCESS_STATE_CACHED_RECENT = 1017;
    public static final int PROCESS_STATE_CACHED_EMPTY = 1018;
    public static final int PROCESS_STATE_NONEXISTENT = 1019;

    // enum OomChangeReasonEnum
    public static final int OOM_ADJ_REASON_UNKNOWN_TO_PROTO = -1;
    public static final int OOM_ADJ_REASON_NONE = 0;
    public static final int OOM_ADJ_REASON_ACTIVITY = 1;
    public static final int OOM_ADJ_REASON_FINISH_RECEIVER = 2;
    public static final int OOM_ADJ_REASON_START_RECEIVER = 3;
    public static final int OOM_ADJ_REASON_BIND_SERVICE = 4;
    public static final int OOM_ADJ_REASON_UNBIND_SERVICE = 5;
    public static final int OOM_ADJ_REASON_START_SERVICE = 6;
    public static final int OOM_ADJ_REASON_GET_PROVIDER = 7;
    public static final int OOM_ADJ_REASON_REMOVE_PROVIDER = 8;
    public static final int OOM_ADJ_REASON_UI_VISIBILITY = 9;
    public static final int OOM_ADJ_REASON_ALLOWLIST = 10;
    public static final int OOM_ADJ_REASON_PROCESS_BEGIN = 11;
    public static final int OOM_ADJ_REASON_PROCESS_END = 12;
    public static final int OOM_ADJ_REASON_SHORT_FGS_TIMEOUT = 13;
    public static final int OOM_ADJ_REASON_SYSTEM_INIT = 14;
    public static final int OOM_ADJ_REASON_BACKUP = 15;
    public static final int OOM_ADJ_REASON_SHELL = 16;
    public static final int OOM_ADJ_REASON_REMOVE_TASK = 17;
    public static final int OOM_ADJ_REASON_UID_IDLE = 18;
    public static final int OOM_ADJ_REASON_STOP_SERVICE = 19;
    public static final int OOM_ADJ_REASON_EXECUTING_SERVICE = 20;
    public static final int OOM_ADJ_REASON_RESTRICTION_CHANGE = 21;
    public static final int OOM_ADJ_REASON_COMPONENT_DISABLED = 22;

    // enum AppOpEnum
    public static final int APP_OP_NONE = -1;
    public static final int APP_OP_COARSE_LOCATION = 0;
    public static final int APP_OP_FINE_LOCATION = 1;
    public static final int APP_OP_GPS = 2;
    public static final int APP_OP_VIBRATE = 3;
    public static final int APP_OP_READ_CONTACTS = 4;
    public static final int APP_OP_WRITE_CONTACTS = 5;
    public static final int APP_OP_READ_CALL_LOG = 6;
    public static final int APP_OP_WRITE_CALL_LOG = 7;
    public static final int APP_OP_READ_CALENDAR = 8;
    public static final int APP_OP_WRITE_CALENDAR = 9;
    public static final int APP_OP_WIFI_SCAN = 10;
    public static final int APP_OP_POST_NOTIFICATION = 11;
    public static final int APP_OP_NEIGHBORING_CELLS = 12;
    public static final int APP_OP_CALL_PHONE = 13;
    public static final int APP_OP_READ_SMS = 14;
    public static final int APP_OP_WRITE_SMS = 15;
    public static final int APP_OP_RECEIVE_SMS = 16;
    public static final int APP_OP_RECEIVE_EMERGENCY_SMS = 17;
    public static final int APP_OP_RECEIVE_MMS = 18;
    public static final int APP_OP_RECEIVE_WAP_PUSH = 19;
    public static final int APP_OP_SEND_SMS = 20;
    public static final int APP_OP_READ_ICC_SMS = 21;
    public static final int APP_OP_WRITE_ICC_SMS = 22;
    public static final int APP_OP_WRITE_SETTINGS = 23;
    public static final int APP_OP_SYSTEM_ALERT_WINDOW = 24;
    public static final int APP_OP_ACCESS_NOTIFICATIONS = 25;
    public static final int APP_OP_CAMERA = 26;
    public static final int APP_OP_RECORD_AUDIO = 27;
    public static final int APP_OP_PLAY_AUDIO = 28;
    public static final int APP_OP_READ_CLIPBOARD = 29;
    public static final int APP_OP_WRITE_CLIPBOARD = 30;
    public static final int APP_OP_TAKE_MEDIA_BUTTONS = 31;
    public static final int APP_OP_TAKE_AUDIO_FOCUS = 32;
    public static final int APP_OP_AUDIO_MASTER_VOLUME = 33;
    public static final int APP_OP_AUDIO_VOICE_VOLUME = 34;
    public static final int APP_OP_AUDIO_RING_VOLUME = 35;
    public static final int APP_OP_AUDIO_MEDIA_VOLUME = 36;
    public static final int APP_OP_AUDIO_ALARM_VOLUME = 37;
    public static final int APP_OP_AUDIO_NOTIFICATION_VOLUME = 38;
    public static final int APP_OP_AUDIO_BLUETOOTH_VOLUME = 39;
    public static final int APP_OP_WAKE_LOCK = 40;
    public static final int APP_OP_MONITOR_LOCATION = 41;
    public static final int APP_OP_MONITOR_HIGH_POWER_LOCATION = 42;
    public static final int APP_OP_GET_USAGE_STATS = 43;
    public static final int APP_OP_MUTE_MICROPHONE = 44;
    public static final int APP_OP_TOAST_WINDOW = 45;
    public static final int APP_OP_PROJECT_MEDIA = 46;
    public static final int APP_OP_ACTIVATE_VPN = 47;
    public static final int APP_OP_WRITE_WALLPAPER = 48;
    public static final int APP_OP_ASSIST_STRUCTURE = 49;
    public static final int APP_OP_ASSIST_SCREENSHOT = 50;
    public static final int APP_OP_READ_PHONE_STATE = 51;
    public static final int APP_OP_ADD_VOICEMAIL = 52;
    public static final int APP_OP_USE_SIP = 53;
    public static final int APP_OP_PROCESS_OUTGOING_CALLS = 54;
    public static final int APP_OP_USE_FINGERPRINT = 55;
    public static final int APP_OP_BODY_SENSORS = 56;
    public static final int APP_OP_READ_CELL_BROADCASTS = 57;
    public static final int APP_OP_MOCK_LOCATION = 58;
    public static final int APP_OP_READ_EXTERNAL_STORAGE = 59;
    public static final int APP_OP_WRITE_EXTERNAL_STORAGE = 60;
    public static final int APP_OP_TURN_SCREEN_ON = 61;
    public static final int APP_OP_GET_ACCOUNTS = 62;
    public static final int APP_OP_RUN_IN_BACKGROUND = 63;
    public static final int APP_OP_AUDIO_ACCESSIBILITY_VOLUME = 64;
    public static final int APP_OP_READ_PHONE_NUMBERS = 65;
    public static final int APP_OP_REQUEST_INSTALL_PACKAGES = 66;
    public static final int APP_OP_PICTURE_IN_PICTURE = 67;
    public static final int APP_OP_INSTANT_APP_START_FOREGROUND = 68;
    public static final int APP_OP_ANSWER_PHONE_CALLS = 69;
    public static final int APP_OP_RUN_ANY_IN_BACKGROUND = 70;
    public static final int APP_OP_CHANGE_WIFI_STATE = 71;
    public static final int APP_OP_REQUEST_DELETE_PACKAGES = 72;
    public static final int APP_OP_BIND_ACCESSIBILITY_SERVICE = 73;
    public static final int APP_OP_ACCEPT_HANDOVER = 74;
    public static final int APP_OP_MANAGE_IPSEC_TUNNELS = 75;
    public static final int APP_OP_START_FOREGROUND = 76;
    public static final int APP_OP_BLUETOOTH_SCAN = 77;
    public static final int APP_OP_USE_BIOMETRIC = 78;
    public static final int APP_OP_ACTIVITY_RECOGNITION = 79;
    public static final int APP_OP_SMS_FINANCIAL_TRANSACTIONS = 80;
    public static final int APP_OP_READ_MEDIA_AUDIO = 81;
    public static final int APP_OP_WRITE_MEDIA_AUDIO = 82;
    public static final int APP_OP_READ_MEDIA_VIDEO = 83;
    public static final int APP_OP_WRITE_MEDIA_VIDEO = 84;
    public static final int APP_OP_READ_MEDIA_IMAGES = 85;
    public static final int APP_OP_WRITE_MEDIA_IMAGES = 86;
    public static final int APP_OP_LEGACY_STORAGE = 87;
    public static final int APP_OP_ACCESS_ACCESSIBILITY = 88;
    public static final int APP_OP_READ_DEVICE_IDENTIFIERS = 89;
    public static final int APP_OP_ACCESS_MEDIA_LOCATION = 90;
    public static final int APP_OP_QUERY_ALL_PACKAGES = 91;
    public static final int APP_OP_MANAGE_EXTERNAL_STORAGE = 92;
    public static final int APP_OP_INTERACT_ACROSS_PROFILES = 93;
    public static final int APP_OP_ACTIVATE_PLATFORM_VPN = 94;
    public static final int APP_OP_LOADER_USAGE_STATS = 95;
    public static final int APP_OP_DEPRECATED_1 = 96;
    public static final int APP_OP_AUTO_REVOKE_PERMISSIONS_IF_UNUSED = 97;
    public static final int APP_OP_AUTO_REVOKE_MANAGED_BY_INSTALLER = 98;
    public static final int APP_OP_NO_ISOLATED_STORAGE = 99;
    public static final int APP_OP_PHONE_CALL_MICROPHONE = 100;
    public static final int APP_OP_PHONE_CALL_CAMERA = 101;
    public static final int APP_OP_RECORD_AUDIO_HOTWORD = 102;
    public static final int APP_OP_MANAGE_ONGOING_CALLS = 103;
    public static final int APP_OP_MANAGE_CREDENTIALS = 104;
    public static final int APP_OP_USE_ICC_AUTH_WITH_DEVICE_IDENTIFIER = 105;
    public static final int APP_OP_RECORD_AUDIO_OUTPUT = 106;
    public static final int APP_OP_SCHEDULE_EXACT_ALARM = 107;
    public static final int APP_OP_FINE_LOCATION_SOURCE = 108;
    public static final int APP_OP_COARSE_LOCATION_SOURCE = 109;
    public static final int APP_OP_MANAGE_MEDIA = 110;
    public static final int APP_OP_BLUETOOTH_CONNECT = 111;
    public static final int APP_OP_UWB_RANGING = 112;
    public static final int APP_OP_ACTIVITY_RECOGNITION_SOURCE = 113;
    public static final int APP_OP_BLUETOOTH_ADVERTISE = 114;
    public static final int APP_OP_RECORD_INCOMING_PHONE_AUDIO = 115;
    public static final int APP_OP_NEARBY_WIFI_DEVICES = 116;
    public static final int APP_OP_ESTABLISH_VPN_SERVICE = 117;
    public static final int APP_OP_ESTABLISH_VPN_MANAGER = 118;
    public static final int APP_OP_ACCESS_RESTRICTED_SETTINGS = 119;
    public static final int APP_OP_RECEIVE_AMBIENT_TRIGGER_AUDIO = 120;
    public static final int APP_OP_RECEIVE_EXPLICIT_USER_INTERACTION_AUDIO = 121;
    public static final int APP_OP_RUN_USER_INITIATED_JOBS = 122;
    public static final int APP_OP_READ_MEDIA_VISUAL_USER_SELECTED = 123;
    public static final int APP_OP_SYSTEM_EXEMPT_FROM_SUSPENSION = 124;
    public static final int APP_OP_SYSTEM_EXEMPT_FROM_DISMISSIBLE_NOTIFICATIONS = 125;
    public static final int APP_OP_READ_WRITE_HEALTH_DATA = 126;
    public static final int APP_OP_FOREGROUND_SERVICE_SPECIAL_USE = 127;
    public static final int APP_OP_SYSTEM_EXEMPT_FROM_POWER_RESTRICTIONS = 128;
    public static final int APP_OP_SYSTEM_EXEMPT_FROM_HIBERNATION = 129;
    public static final int APP_OP_SYSTEM_EXEMPT_FROM_ACTIVITY_BG_START_RESTRICTION = 130;
    public static final int APP_OP_CAPTURE_CONSENTLESS_BUGREPORT_ON_USERDEBUG_BUILD = 131;
    public static final int APP_OP_BODY_SENSORS_WRIST_TEMPERATURE = 132;
    public static final int APP_OP_USE_FULL_SCREEN_INTENT = 133;
    public static final int APP_OP_CAMERA_SANDBOXED = 134;
    public static final int APP_OP_RECORD_AUDIO_SANDBOXED = 135;
    public static final int APP_OP_RECEIVE_SANDBOX_TRIGGER_AUDIO = 136;
    public static final int APP_OP_RECEIVE_SANDBOXED_DETECTION_TRAINING_DATA = 137;
    public static final int APP_OP_CREATE_ACCESSIBILITY_OVERLAY = 138;
    public static final int APP_OP_MEDIA_ROUTING_CONTROL = 139;
    public static final int APP_OP_ENABLE_MOBILE_DATA_BY_USER = 140;
    public static final int APP_OP_RESERVED_FOR_TESTING = 141;
    public static final int APP_OP_RAPID_CLEAR_NOTIFICATIONS_BY_LISTENER = 142;

    // enum AppExitReasonCode
    public static final int REASON_UNKNOWN = 0;
    public static final int REASON_EXIT_SELF = 1;
    public static final int REASON_SIGNALED = 2;
    public static final int REASON_LOW_MEMORY = 3;
    public static final int REASON_CRASH = 4;
    public static final int REASON_CRASH_NATIVE = 5;
    public static final int REASON_ANR = 6;
    public static final int REASON_INITIALIZATION_FAILURE = 7;
    public static final int REASON_PERMISSION_CHANGE = 8;
    public static final int REASON_EXCESSIVE_RESOURCE_USAGE = 9;
    public static final int REASON_USER_REQUESTED = 10;
    public static final int REASON_USER_STOPPED = 11;
    public static final int REASON_DEPENDENCY_DIED = 12;
    public static final int REASON_OTHER = 13;
    public static final int REASON_FREEZER = 14;
    public static final int REASON_PACKAGE_STATE_CHANGE = 15;
    public static final int REASON_PACKAGE_UPDATED = 16;

    // enum AppExitSubReasonCode
    public static final int SUBREASON_UNKNOWN = 0;
    public static final int SUBREASON_WAIT_FOR_DEBUGGER = 1;
    public static final int SUBREASON_TOO_MANY_CACHED = 2;
    public static final int SUBREASON_TOO_MANY_EMPTY = 3;
    public static final int SUBREASON_TRIM_EMPTY = 4;
    public static final int SUBREASON_LARGE_CACHED = 5;
    public static final int SUBREASON_MEMORY_PRESSURE = 6;
    public static final int SUBREASON_EXCESSIVE_CPU = 7;
    public static final int SUBREASON_SYSTEM_UPDATE_DONE = 8;
    public static final int SUBREASON_KILL_ALL_FG = 9;
    public static final int SUBREASON_KILL_ALL_BG_EXCEPT = 10;
    public static final int SUBREASON_KILL_UID = 11;
    public static final int SUBREASON_KILL_PID = 12;
    public static final int SUBREASON_INVALID_START = 13;
    public static final int SUBREASON_INVALID_STATE = 14;
    public static final int SUBREASON_IMPERCEPTIBLE = 15;
    public static final int SUBREASON_REMOVE_LRU = 16;
    public static final int SUBREASON_ISOLATED_NOT_NEEDED = 17;
    public static final int SUBREASON_CACHED_IDLE_FORCED_APP_STANDBY = 18;
    public static final int SUBREASON_FREEZER_BINDER_IOCTL = 19;
    public static final int SUBREASON_FREEZER_BINDER_TRANSACTION = 20;
    public static final int SUBREASON_FORCE_STOP = 21;
    public static final int SUBREASON_REMOVE_TASK = 22;
    public static final int SUBREASON_STOP_APP = 23;
    public static final int SUBREASON_KILL_BACKGROUND = 24;
    public static final int SUBREASON_PACKAGE_UPDATE = 25;
    public static final int SUBREASON_UNDELIVERED_BROADCAST = 26;
    public static final int SUBREASON_SDK_SANDBOX_DIED = 27;
    public static final int SUBREASON_SDK_SANDBOX_NOT_NEEDED = 28;
    public static final int SUBREASON_EXCESSIVE_BINDER_OBJECTS = 29;
    public static final int SUBREASON_OOM_KILL = 30;
    public static final int SUBREASON_FREEZER_BINDER_ASYNC_FULL = 31;

    // enum Importance
    public static final int IMPORTANCE_FOREGROUND = 100;
    public static final int IMPORTANCE_FOREGROUND_SERVICE = 125;
    public static final int IMPORTANCE_TOP_SLEEPING_PRE_28 = 150;
    public static final int IMPORTANCE_VISIBLE = 200;
    public static final int IMPORTANCE_PERCEPTIBLE_PRE_26 = 130;
    public static final int IMPORTANCE_PERCEPTIBLE = 230;
    public static final int IMPORTANCE_CANT_SAVE_STATE_PRE_26 = 170;
    public static final int IMPORTANCE_SERVICE = 300;
    public static final int IMPORTANCE_TOP_SLEEPING = 325;
    public static final int IMPORTANCE_CANT_SAVE_STATE = 350;
    public static final int IMPORTANCE_CACHED = 400;
    public static final int IMPORTANCE_BACKGROUND = 400;
    public static final int IMPORTANCE_EMPTY = 500;
    public static final int IMPORTANCE_GONE = 1000;

    // enum ResourceApiEnum
    public static final int RESOURCE_API_NONE = 0;
    public static final int RESOURCE_API_GET_VALUE = 1;
    public static final int RESOURCE_API_RETRIEVE_ATTRIBUTES = 2;

    // enum GameMode
    public static final int GAME_MODE_UNSPECIFIED = 0;
    public static final int GAME_MODE_UNSUPPORTED = 1;
    public static final int GAME_MODE_STANDARD = 2;
    public static final int GAME_MODE_PERFORMANCE = 3;
    public static final int GAME_MODE_BATTERY = 4;
    public static final int GAME_MODE_CUSTOM = 5;

    // enum FgsTypePolicyCheckEnum
    public static final int FGS_TYPE_POLICY_CHECK_UNKNOWN = 0;
    public static final int FGS_TYPE_POLICY_CHECK_OK = 1;
    public static final int FGS_TYPE_POLICY_CHECK_DEPRECATED = 2;
    public static final int FGS_TYPE_POLICY_CHECK_DISABLED = 3;
    public static final int FGS_TYPE_POLICY_CHECK_PERMISSION_DENIED_PERMISSIVE = 4;
    public static final int FGS_TYPE_POLICY_CHECK_PERMISSION_DENIED_ENFORCED = 5;

    // enum HostingComponentType
    public static final int HOSTING_COMPONENT_TYPE_EMPTY = 0;
    public static final int HOSTING_COMPONENT_TYPE_SYSTEM = 1;
    public static final int HOSTING_COMPONENT_TYPE_PERSISTENT = 2;
    public static final int HOSTING_COMPONENT_TYPE_BACKUP = 4;
    public static final int HOSTING_COMPONENT_TYPE_INSTRUMENTATION = 8;
    public static final int HOSTING_COMPONENT_TYPE_ACTIVITY = 16;
    public static final int HOSTING_COMPONENT_TYPE_BROADCAST_RECEIVER = 32;
    public static final int HOSTING_COMPONENT_TYPE_PROVIDER = 64;
    public static final int HOSTING_COMPONENT_TYPE_STARTED_SERVICE = 128;
    public static final int HOSTING_COMPONENT_TYPE_FOREGROUND_SERVICE = 256;
    public static final int HOSTING_COMPONENT_TYPE_BOUND_SERVICE = 512;

    // enum BroadcastType
    public static final int BROADCAST_TYPE_NONE = 0;
    public static final int BROADCAST_TYPE_BACKGROUND = 1;
    public static final int BROADCAST_TYPE_FOREGROUND = 2;
    public static final int BROADCAST_TYPE_ALARM = 4;
    public static final int BROADCAST_TYPE_INTERACTIVE = 8;
    public static final int BROADCAST_TYPE_ORDERED = 16;
    public static final int BROADCAST_TYPE_PRIORITIZED = 32;
    public static final int BROADCAST_TYPE_RESULT_TO = 64;
    public static final int BROADCAST_TYPE_DEFERRABLE_UNTIL_ACTIVE = 128;
    public static final int BROADCAST_TYPE_PUSH_MESSAGE = 256;
    public static final int BROADCAST_TYPE_PUSH_MESSAGE_OVER_QUOTA = 512;
    public static final int BROADCAST_TYPE_STICKY = 1024;
    public static final int BROADCAST_TYPE_INITIAL_STICKY = 2048;

    // enum BroadcastDeliveryGroupPolicy
    public static final int BROADCAST_DELIVERY_GROUP_POLICY_ALL = 0;
    public static final int BROADCAST_DELIVERY_GROUP_POLICY_MOST_RECENT = 1;
    public static final int BROADCAST_DELIVERY_GROUP_POLICY_MERGED = 2;

    // enum AppStartStartupState
    public static final int STARTUP_STATE_STARTED = 0;
    public static final int STARTUP_STATE_ERROR = 1;
    public static final int STARTUP_STATE_FIRST_FRAME_DRAWN = 2;

    // enum AppStartReasonCode
    public static final int START_REASON_ALARM = 0;
    public static final int START_REASON_BACKUP = 1;
    public static final int START_REASON_BOOT_COMPLETE = 2;
    public static final int START_REASON_BROADCAST = 3;
    public static final int START_REASON_CONTENT_PROVIDER = 4;
    public static final int START_REASON_JOB = 5;
    public static final int START_REASON_LAUNCHER = 6;
    public static final int START_REASON_OTHER = 7;
    public static final int START_REASON_PUSH = 8;
    public static final int START_REASON_RESUMED_ACTIVITY = 9;
    public static final int START_REASON_SERVICE = 10;
    public static final int START_REASON_START_ACTIVITY = 11;

    // enum AppStartStartType
    public static final int START_TYPE_COLD = 0;
    public static final int START_TYPE_WARM = 1;
    public static final int START_TYPE_HOT = 2;

    // enum AppStartLaunchMode
    public static final int LAUNCH_MODE_STANDARD = 0;
    public static final int LAUNCH_MODE_SINGLE_TOP = 1;
    public static final int LAUNCH_MODE_SINGLE_INSTANCE = 2;
    public static final int LAUNCH_MODE_SINGLE_TASK = 3;
    public static final int LAUNCH_MODE_SINGLE_INSTANCE_PER_TASK = 4;

}
