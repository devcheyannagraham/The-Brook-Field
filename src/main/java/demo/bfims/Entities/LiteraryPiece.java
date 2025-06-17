package demo.bfims.Entities;

import demo.bfims.Enums.LiteraryType;
import jakarta.persistence.*;

@Entity
public class LiteraryPiece extends Book {
    @Enumerated(EnumType.STRING)
    LiteraryType type;

    public LiteraryType getType() {
        return type;
    }
    public void setType(LiteraryType type) {
        this.type = type;
    }


}
