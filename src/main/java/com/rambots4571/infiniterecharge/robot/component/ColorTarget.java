package com.rambots4571.infiniterecharge.robot.component;

public enum ColorTarget {
    Blue {
        @Override
        public ColorTarget getComplement() {
            return Red;
        }
    }, Green {
        @Override
        public ColorTarget getComplement() {
            return Yellow;
        }
    }, Red {
        @Override
        public ColorTarget getComplement() {
            return Blue;
        }
    }, Yellow {
        @Override
        public ColorTarget getComplement() {
            return Green;
        }
    }, Unknown {
        @Override
        public ColorTarget getComplement() {
            return Unknown;
        }
    };

    /**
     * Because the color sensor on the control panel is 90º away from where
     * the arm is placed, you need a method that will return what color the
     * arm needs to sense for the field to get the right color.
     *
     * @return color 90º away from the control panel color sensor.
     */
    public abstract ColorTarget getComplement();
}
