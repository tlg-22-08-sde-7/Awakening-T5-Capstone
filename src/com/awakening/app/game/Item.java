package com.awakening.app.game;


import java.util.ArrayList;
import java.util.List;

public class Item {

    private Item.FrontDoorKey frontDoorKey;
    private Item.Journal journal;
    private Item.Batteries batteries;
    private Item.PatientFile patientFile;
    private Item.Bandages bandages;
    private Item.PressPass pressPass;
    private Item.CellPhone cellPhone;
    private Item.PaperClip paperClip;
    private Item.Camera camera;
    private Item.Tylenol tylenol;
    private Item.MasterKey masterKey;
    private Item.WoodCane woodCane;
    private Item.FirstAidKit firstAidKit;
    private Item.Axe axe;
    private Item.Picture picture;
    private Item.FireExtinguisher fireExtinguisher;
    private Item.Barbell barbell;
    private Item.RecordsKey recordsKey;



    public List<ItemsSetup> loadItems(){
        List<Item.ItemsSetup> itemList = new ArrayList<>();
        itemList.add(frontDoorKey);
        itemList.add(journal);
        itemList.add(batteries);
        itemList.add(patientFile);
        itemList.add(bandages);
        itemList.add(pressPass);
        itemList.add(cellPhone);
        itemList.add(paperClip);
        itemList.add(camera);
        itemList.add(tylenol);
        itemList.add(masterKey);
        itemList.add(woodCane);
        itemList.add(fireExtinguisher);
        itemList.add(firstAidKit);
        itemList.add(axe);
        itemList.add(picture);
        itemList.add(barbell);
        itemList.add(recordsKey);

        return itemList;
    }






    public ItemsSetup findItem(String itemName){
        if (itemName.equals(recordsKey.getName())) {
            return recordsKey;
        }
        if(itemName.equals(frontDoorKey.getName())){
            return frontDoorKey;
        }
        if(itemName.equals(journal.getName())){
            return journal;
        }
        if(itemName.equals(batteries.getName())){
            return batteries;
        }
        if(itemName.equals(patientFile.getName())){
            return patientFile;
        }
        if(itemName.equals(bandages.getName())){
            return bandages;
        }
        if(itemName.equals(pressPass.getName())){
            return pressPass;
        }
        if(itemName.equals(cellPhone.getName())){
            return cellPhone;
        }
        if(itemName.equals(paperClip.getName())){
            return paperClip;
        }
        if(itemName.equals(camera.getName())){
            return camera;
        }
        if(itemName.equals(tylenol.getName())){
            return tylenol;
        }
        if(itemName.equals(masterKey.getName())){
            return masterKey;
        }
        if(itemName.equals(woodCane.getName())){
            return woodCane;
        }
        if(itemName.equals(fireExtinguisher.getName())){
            return fireExtinguisher;
        }
        if(itemName.equals(firstAidKit.getName())){
            return firstAidKit;
        }
        if(itemName.equals(picture.getName())){
            return picture;
        }
        if(itemName.equals(axe.getName())){
            return axe;
        }
        if(itemName.equals(barbell.getName())){
            return barbell;
        }
        else
            return null;
    }

    public RecordsKey getRecordsKey() {
        return recordsKey;
    }

    public void setRecordsKey(RecordsKey recordsKey) {
        this.recordsKey = recordsKey;
    }

    public Item.FrontDoorKey getFrontDoorKey() {
        return frontDoorKey;
    }

    public void setFrontDoorKey(Item.FrontDoorKey frontDoorKey) {
        this.frontDoorKey = frontDoorKey;
    }

    public Item.Journal getJournal() {
        return journal;
    }

    public void setJournal(Item.Journal journal) {
        this.journal = journal;
    }

    public Item.Batteries getBatteries() {
        return batteries;
    }

    public void setBatteries(Item.Batteries batteries) {
        this.batteries = batteries;
    }

    public Item.PatientFile getPatientFile() {
        return patientFile;
    }

    public void setPatientFile(Item.PatientFile patientFile) {
        this.patientFile = patientFile;
    }

    public Item.Bandages getBandages() {
        return bandages;
    }

    public void setBandages(Item.Bandages bandages) {
        this.bandages = bandages;
    }

    public Item.PressPass getPressPass() {
        return pressPass;
    }

    public void setPressPass(Item.PressPass pressPass) {
        this.pressPass = pressPass;
    }

    public Item.CellPhone getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(Item.CellPhone cellPhone) {
        this.cellPhone = cellPhone;
    }

    public Item.PaperClip getPaperClip() {
        return paperClip;
    }

    public void setPaperClip(Item.PaperClip paperClip) {
        this.paperClip = paperClip;
    }

    public Item.Camera getCamera() {
        return camera;
    }

    public void setCamera(Item.Camera camera) {
        this.camera = camera;
    }

    public Tylenol getTylenol() {
        return tylenol;
    }

    public void setTylenol(Tylenol tylenol) {
        this.tylenol = tylenol;
    }

    public MasterKey getMasterKey() {
        return masterKey;
    }

    public void setMasterKey(MasterKey masterKey) {
        this.masterKey = masterKey;
    }

    public WoodCane getWoodCane() {
        return woodCane;
    }

    public void setWoodCane(WoodCane woodCane) {
        this.woodCane = woodCane;
    }

    public FirstAidKit getFirstAidKit() {
        return firstAidKit;
    }

    public void setFirstAidKit(FirstAidKit firstAidKit) {
        this.firstAidKit = firstAidKit;
    }

    public Axe getAxe() {
        return axe;
    }

    public void setAxe(Axe axe) {
        this.axe = axe;
    }

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }

    public FireExtinguisher getFireExtinguisher() {
        return fireExtinguisher;
    }

    public void setFireExtinguisher(FireExtinguisher fireExtinguisher) {
        this.fireExtinguisher = fireExtinguisher;
    }

    public Barbell getBarbell() {
        return barbell;
    }

    public void setBarbell(Barbell barbell) {
        this.barbell = barbell;
    }

    public static class ItemsSetup{
        private String name;
        private String description;
        private String startLocation;
        private boolean specialItemFlag;
        private int charge;
        private int attackPoints;
        private int healPoints;

        public ItemsSetup(){ }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getStartLocation() {
            return startLocation;
        }

        public void setStartLocation(String startLocation) {
            this.startLocation = startLocation;
        }

        public boolean isSpecialItemFlag() {
            return specialItemFlag;
        }

        public void setSpecialItemFlag(boolean specialItemFlag) {
            this.specialItemFlag = specialItemFlag;
        }

        public int getCharge() {
            return charge;
        }

        public void setCharge(int charge) {
            this.charge = charge;
        }

        public int getAttackPoints() {
            return attackPoints;
        }

        public void setAttackPoints(int attackPoints) {
            this.attackPoints = attackPoints;
        }

        public int getHealPoints() {
            return healPoints;
        }

        public void setHealPoints(int healPoints) {
            this.healPoints = healPoints;
        }
    }




//Creation of special classes

    public static class RecordsKey extends Item.ItemsSetup {
        public RecordsKey() {
        }
    }

    public static class FrontDoorKey extends Item.ItemsSetup {
        public FrontDoorKey() {
        }
    }

    public  static class Journal extends Item.ItemsSetup {
        public Journal() {
        }
    }

    public  static class Batteries extends Item.ItemsSetup {
        public Batteries() {
        }
    }

    public  static class PatientFile extends Item.ItemsSetup {
        public PatientFile() {
        }
    }

    public  static class  Bandages extends Item.ItemsSetup {
        public Bandages() {
        }
    }

    public  static class PressPass extends Item.ItemsSetup {
        public PressPass() {
        }
    }

    public  static class CellPhone extends Item.ItemsSetup {
        public CellPhone() {
        }
    }

    public  static class PaperClip extends Item.ItemsSetup {
        public PaperClip() {
        }
    }

    public  static class Camera extends Item.ItemsSetup {
        public Camera() {
        }
    }
    public  static class Picture extends Item.ItemsSetup {
        public Picture() {
        }
    }
    public  static class FireExtinguisher extends Item.ItemsSetup {
        public FireExtinguisher() {
        }
    }
    public  static class Axe extends Item.ItemsSetup {
        public Axe() {
        }
    }
    public  static class FirstAidKit extends Item.ItemsSetup {
        public FirstAidKit() {
        }
    }
    public  static class Barbell extends Item.ItemsSetup {
        public Barbell() {
        }
    }
    public  static class WoodCane extends Item.ItemsSetup {
        public WoodCane() {
        }
    }
    public  static class MasterKey extends Item.ItemsSetup {
        public MasterKey() {
        }
    }
    public  static class Tylenol extends Item.ItemsSetup {
        public Tylenol() {
        }
    }

}
