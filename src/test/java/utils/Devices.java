package utils;



public enum Devices {


    Emulator("Pixel","9","Emulator"),
    SamsungA10("RZ8M422FZJA", "9","samsungA10"),
    Infinix("0328724939000205","9","Infinix"),
    Moto("ZH33L2JWWT","7.1.1","Moto"),
    SamsungJ7("330033acecf394bd","8.1","SamsungJ7");


    private String name;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    private String version;
    public String getVersion() {
        return version;
    }
    public void setVersion(String version) {
        this.version = version;
    }

    private String devicename;
    public String getDevicename() {return devicename;}
    public void setDevicename (String devicename){this.devicename = devicename;}


    Devices(String name, String version, String devicename) {
        this.name = name;
        this.version = version;
        this.devicename = devicename;
    }


}
