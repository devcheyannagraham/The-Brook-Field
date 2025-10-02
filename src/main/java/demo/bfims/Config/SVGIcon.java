package demo.bfims.Config;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class SVGIcon {
    String viewBox;
    @Column(length=1000)
    String pathD;
    String className;
    String xmlns;

    public SVGIcon() {
    }

    public SVGIcon(String xmlns, String viewBox, String pathD, String className) {
        this.viewBox = viewBox;
        this.pathD = pathD;
        this.className = className;
        this.xmlns = xmlns;
    }

    public String getViewBox() {
        return viewBox;
    }

    public void setViewBox(String viewBox) {
        this.viewBox = viewBox;
    }

    public String getPathD() {
        return pathD;
    }

    public void setPathD(String path) {
        this.pathD = path;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getXmlns() {
        return xmlns;
    }

    public void setXmlns(String xmlns) {
        this.xmlns = xmlns;
    }

    @Override
    public String toString() {
        return "SVGIcon{" +
                "viewBox='" + viewBox + '\'' +
                ", path='" + pathD + '\'' +
                ", className='" + className + '\'' +
                ", xmlns='" + xmlns + '\'' +
                '}';
    }
}

