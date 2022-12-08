package com.awakening.app.game;

public class GameText {
    private StartText startText;
    private MorgueText morgueText;
    private OfficeText officeText;
    private FrontDeskText frontDeskText;
    private EmergencyRoomText emergencyRoomText;
    private PatientRoomText patientRoomText;
    private HallwayText hallwayText;
    private BasementText basementText;


    public StartText getStartText() {
        return startText;
    }

    public void setStartText(StartText startText) {
        this.startText = startText;
    }

    public MorgueText getMorgueText() {
        return morgueText;
    }

    public void setMorgueText(MorgueText morgueText) {
        this.morgueText = morgueText;
    }

    public OfficeText getOfficeText() {
        return officeText;
    }

    public void setOfficeText(OfficeText officeText) {
        this.officeText = officeText;
    }

    public FrontDeskText getFrontDeskText() {
        return frontDeskText;
    }

    public void setFrontDeskText(FrontDeskText frontDeskText) {
        this.frontDeskText = frontDeskText;
    }

    public EmergencyRoomText getEmergencyRoomText() {
        return emergencyRoomText;
    }

    public void setEmergencyRoomText(EmergencyRoomText emergencyRoomText) {
        this.emergencyRoomText = emergencyRoomText;
    }

    public PatientRoomText getPatientRoomText() {
        return patientRoomText;
    }

    public void setPatientRoomText(PatientRoomText patientRoomText) {
        this.patientRoomText = patientRoomText;
    }

    public HallwayText getHallwayText() {
        return hallwayText;
    }

    public void setHallwayText(HallwayText hallwayText) {
        this.hallwayText = hallwayText;
    }

    public BasementText getBasementText() {
        return basementText;
    }

    public void setBasementText(BasementText basementText) {
        this.basementText = basementText;
    }

    public static class GameTextLayout {
        private String text;

        public GameTextLayout(){}

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }

    public static class StartText extends GameTextLayout{
        public StartText(){}
    }

    public static class MorgueText extends GameTextLayout{
        public MorgueText(){}
    }

    public static class OfficeText extends GameTextLayout{
        public OfficeText(){}
    }

    public static class FrontDeskText extends GameTextLayout{
        public FrontDeskText(){}
    }

    public static class EmergencyRoomText extends GameTextLayout {
        public EmergencyRoomText(){}
    }

    public static class PatientRoomText extends GameTextLayout{
        public PatientRoomText(){}
    }

    public static class HallwayText extends GameTextLayout{
        public HallwayText(){}
    }

    public static class BasementText extends GameTextLayout{
        public BasementText(){}
    }
}
