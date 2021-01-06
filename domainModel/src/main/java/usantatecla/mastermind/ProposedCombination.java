package usantatecla.mastermind;

import usantatecla.utils.Console;
import java.util.ArrayList;

class ProposedCombination extends Combination {

    void write() {
        for (Color color : this.colors) {
            color.write();
        }
    }

    void read() {
        Error error;
        do {
            Message.PROPOSED_COMBINATION.write();
            error = this.checkError(Console.getInstance().readString());
            error.writeln();
            if (!error.isNull()) {
                this.colors = new ArrayList<>();
            }
        } while (!error.isNull());
    }

    private Error checkError(String characters) {
        if (characters.length() != Result.WIDTH) {
            return Error.WRONG_LENGTH;
        }
        for (int i = 0; i < characters.length(); i++) {
            Color color = Color.getInstance(characters.charAt(i));
            if (color.isNull()) {
                return Error.WRONG_CHARACTERS;
            }
            for (int j = 0; j < i; j++) {
                if (this.colors.get(j) == color) {
                    return Error.DUPLICATED;
                }
            }
            this.colors.add(color);
        }
        return Error.NULL_ERROR;
    }

    boolean contains(Color color, int position) {
        assert position < this.colors.size();

        return this.colors.get(position) == color;
    }

    boolean contains(Color color) {
        return this.colors.contains(color);
    }

}
