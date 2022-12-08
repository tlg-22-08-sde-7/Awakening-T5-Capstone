package com.awakening.app.game;

import java.util.List;

public class RoomMap {

    private Basement basement;
    private Morgue morgue;
    private FilingCabinet filingCabinet;
    private EmergencyRoom emergencyRoom;
    private Office office;
    private Desk desk;
    private FrontDesk frontDesk;
    private Hallway hallway;
    private KeyPad keypad;
    private PatientRoom patientRoom;

    public Basement getBasement() {
        return basement;
    }

    public void setBasement(Basement basement) {
        this.basement = basement;
    }

    public Morgue getMorgue() {
        return morgue;
    }

    public void setMorgue(Morgue morgue) {
        this.morgue = morgue;
    }

    public FilingCabinet getFilingCabinet() {
        return filingCabinet;
    }

    public void setFilingCabinet(FilingCabinet filingCabinet) {
        this.filingCabinet = filingCabinet;
    }

    public EmergencyRoom getEmergencyRoom() {
        return emergencyRoom;
    }

    public void setEmergencyRoom(EmergencyRoom emergencyRoom) {
        this.emergencyRoom = emergencyRoom;
    }

    public Office getOffice() {
        return office;
    }

    public void setOffice(Office office) {
        this.office = office;
    }

    public Desk getDesk() {
        return desk;
    }

    public void setDesk(Desk desk) {
        this.desk = desk;
    }

    public FrontDesk getFrontDesk() {
        return frontDesk;
    }

    public void setFrontDesk(FrontDesk frontDesk) {
        this.frontDesk = frontDesk;
    }

    public Hallway getHallway() {
        return hallway;
    }

    public void setHallway(Hallway hallway) {
        this.hallway = hallway;
    }

    public KeyPad getKeypad() {
        return keypad;
    }

    public void setKeypad(KeyPad keypad) {
        this.keypad = keypad;
    }

    public PatientRoom getPatientRoom() {
        return patientRoom;
    }

    public void setPatientRoom(PatientRoom patientRoom) {
        this.patientRoom = patientRoom;
    }

    private static class RoomLayout {
        private String name;
        private Directions directions;
        private String description;
        private boolean isLocked;
        private List<String> items;

        public RoomLayout() {
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Directions getDirections() {
            return directions;
        }

        public void setDirections(Directions directions) {
            this.directions = directions;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public boolean isLocked() {
            return isLocked;
        }

        public void setLocked(boolean locked) {
            isLocked = locked;
        }

        public List<String> getItems() {
            return items;
        }

        public void setItems(List<String> items) {
            this.items = items;
        }

        private static class Directions {
            private String north;
            private String south;
            private String east;
            private String west;

            public Directions() {
            }

            public String getNorth() {
                return north;
            }

            public void setNorth(String north) {
                this.north = north;
            }

            public String getSouth() {
                return south;
            }

            public void setSouth(String south) {
                this.south = south;
            }

            public String getEast() {
                return east;
            }

            public void setEast(String east) {
                this.east = east;
            }

            public String getWest() {
                return west;
            }

            public void setWest(String west) {
                this.west = west;
            }
        }
    }

    public static class Basement extends RoomLayout {
        public Basement() {
        }

    }

    public static class Morgue extends RoomLayout {
        public Morgue() {
        }
    }

    public  static class FilingCabinet extends RoomLayout {
        public FilingCabinet() {
        }
    }

    public static class EmergencyRoom extends RoomLayout {
        public EmergencyRoom() {
        }
    }

    public static class Office extends RoomLayout {
        public Office() {
        }
    }

    public static class Desk extends RoomLayout {
        public Desk() {
        }
    }

    public static class FrontDesk extends RoomLayout {
        public FrontDesk() {
        }
    }

    public static class Hallway extends RoomLayout {
        public Hallway() {
        }
    }

    public static class KeyPad extends RoomLayout {
        public KeyPad() {
        }
    }

    public static class PatientRoom extends RoomLayout {
        public PatientRoom() {
        }
    }
}


