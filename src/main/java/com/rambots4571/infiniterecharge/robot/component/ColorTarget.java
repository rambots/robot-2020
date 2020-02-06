package com.rambots4571.infiniterecharge.robot.component;

public enum ColorTarget {
    Blue {
        @Override
        public ColorTarget getComplement() {
            return Red;
        }
    },
    Green {
        @Override
        public ColorTarget getComplement() {
            return Yellow;
        }
    },
    Red {
        @Override
        public ColorTarget getComplement() {
            return Blue;
        }
    },
    Yellow {
        @Override
        public ColorTarget getComplement() {
            return Green;
        }
    },
    Unknown {
        @Override
        public ColorTarget getComplement() {
            return Unknown;
        }
    };

    public abstract ColorTarget getComplement();
}
