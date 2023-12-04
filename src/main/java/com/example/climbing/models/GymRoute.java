package com.example.climbing.models;

public  class GymRoute {
        Integer routeId;
        Integer gymId;

        public GymRoute(Integer routeId, Integer gymId) {
            this.routeId = routeId;
            this.gymId = gymId;
        }

        public GymRoute(){}

        public Integer getRouteId() {
            return routeId;
        }

        public void setRouteId(Integer routeId) {
            this.routeId = routeId;
        }

        public Integer getGymId() {
            return gymId;
        }

        public void setGymId(Integer gymId) {
            this.gymId = gymId;
        }
    }