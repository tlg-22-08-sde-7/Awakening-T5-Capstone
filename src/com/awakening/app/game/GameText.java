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
    private GymText gymText;
    private PharmacyText pharmacyText;
    private FellowshipRoomText fellowshipRoomText;
    private FinanceText financeText;
    private UpperHallwayText upperHallwayText;
    private StairsText stairsText;
    private JanitorClosetText janitorClosetText;


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

    public GymText getGymText() {
        return gymText;
    }

    public void setGymText(GymText gymText) {
        this.gymText = gymText;
    }

    public PharmacyText getPharmacyText() {
        return pharmacyText;
    }

    public void setPharmacyText(PharmacyText pharmacyText) {
        this.pharmacyText = pharmacyText;
    }

    public FellowshipRoomText getFellowshipRoomText() {
        return fellowshipRoomText;
    }

    public void setFellowshipRoomText(FellowshipRoomText fellowshipRoomText) {
        this.fellowshipRoomText = fellowshipRoomText;
    }

    public FinanceText getFinanceText() {
        return financeText;
    }

    public void setFinanceText(FinanceText financeText) {
        this.financeText = financeText;
    }

    public UpperHallwayText getUpperHallwayText() {
        return upperHallwayText;
    }

    public void setUpperHallwayText(UpperHallwayText upperHallwayText) {
        this.upperHallwayText = upperHallwayText;
    }

    public StairsText getStairsText() {
        return stairsText;
    }

    public void setStairsText(StairsText stairsText) {
        this.stairsText = stairsText;
    }

    public JanitorClosetText getJanitorClosetText() {
        return janitorClosetText;
    }

    public void setJanitorClosetText(JanitorClosetText janitorClosetText) {
        this.janitorClosetText = janitorClosetText;
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

    public static class UpperHallwayText extends GameTextLayout {
        public UpperHallwayText(){}
    }

    public static class StairsText extends GameTextLayout {
        public StairsText(){}
    }

    public static class FinanceText extends GameTextLayout {
        public FinanceText(){}
    }

    public static class FellowshipRoomText extends GameTextLayout {
        public FellowshipRoomText(){}
    }

    public static class JanitorClosetText extends GameTextLayout {
        public JanitorClosetText(){}
    }

    public static class PharmacyText extends GameTextLayout {
        public PharmacyText(){}
    }

    public static class GymText extends GameTextLayout {
        public GymText(){}
    }
}
