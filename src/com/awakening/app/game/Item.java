package com.awakening.app.game;

public class Item {

    private Item2.FrontDoorKey frontDoorKey;
    private Item2.Journal journal;
    private Item2.Batteries batteries;
    private Item2.PatientFile patientFile;
    private Item2.Bandages bandages;
    private Item2.PressPass pressPass;
    private Item2.CellPhone cellPhone;
    private Item2.PaperClip paperClip;
    private Item2.Camera camera;





    public Item2.FrontDoorKey getFrontDoorKey() {
        return frontDoorKey;
    }

    public void setFrontDoorKey(Item2.FrontDoorKey frontDoorKey) {
        this.frontDoorKey = frontDoorKey;
    }

    public Item2.Journal getJournal() {
        return journal;
    }

    public void setJournal(Item2.Journal journal) {
        this.journal = journal;
    }

    public Item2.Batteries getBatteries() {
        return batteries;
    }

    public void setBatteries(Item2.Batteries batteries) {
        this.batteries = batteries;
    }

    public Item2.PatientFile getPatientFile() {
        return patientFile;
    }

    public void setPatientFile(Item2.PatientFile patientFile) {
        this.patientFile = patientFile;
    }

    public Item2.Bandages getBandages() {
        return bandages;
    }

    public void setBandages(Item2.Bandages bandages) {
        this.bandages = bandages;
    }

    public Item2.PressPass getPressPass() {
        return pressPass;
    }

    public void setPressPass(Item2.PressPass pressPass) {
        this.pressPass = pressPass;
    }

    public Item2.CellPhone getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(Item2.CellPhone cellPhone) {
        this.cellPhone = cellPhone;
    }

    public Item2.PaperClip getPaperClip() {
        return paperClip;
    }

    public void setPaperClip(Item2.PaperClip paperClip) {
        this.paperClip = paperClip;
    }

    public Item2.Camera getCamera() {
        return camera;
    }

    public void setCamera(Item2.Camera camera) {
        this.camera = camera;
    }

    public static class ItemsSetup{
        private String name;
        private String description;
        private String startLocation;
        private boolean specialItemFlag;
        private int charge;
        private int attackPoints;

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
    }




//Creation of special classes

    public static class FrontDoorKey extends Item2.ItemsSetup {
        public FrontDoorKey() {
        }
    }

    public  static class Journal extends Item2.ItemsSetup {
        public Journal() {
        }
    }

    public  static class Batteries extends Item2.ItemsSetup {
        public Batteries() {
        }
    }

    public  static class PatientFile extends Item2.ItemsSetup {
        public PatientFile() {
        }
    }

    public  static class  Bandages extends Item2.ItemsSetup {
        public Bandages() {
        }
    }

    public  static class PressPass extends Item2.ItemsSetup {
        public PressPass() {
        }
    }

    public  static class CellPhone extends Item2.ItemsSetup {
        public CellPhone() {
        }
    }

    public  static class PaperClip extends Item2.ItemsSetup {
        public PaperClip() {
        }
    }

    public  static class Camera extends Item2.ItemsSetup {
        public Camera() {
        }
    }

}
