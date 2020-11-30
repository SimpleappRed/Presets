package com.wiadevelopers.presets.Model;

import java.io.Serializable;

public class AllPresetsModel implements Serializable {

    int id;
    String categoryDescription;
    String presetCategoryName;
    String presetsDetailsname;
    String PhotoForCategoryBackground;
    String description;
    String[] PresetsDNG_Format;
    String[] PresetsBefore;
    String[] PresetsAfter;

    public AllPresetsModel(int id, String categoryDescription, String presetCategoryName, String presetsDetailsname, String photoForCategoryBackground, String description, String[] presetsDNG_Format, String[] presetsBefore, String[] presetsAfter) {
        this.id = id;
        this.categoryDescription = categoryDescription;
        this.presetCategoryName = presetCategoryName;
        this.presetsDetailsname = presetsDetailsname;
        PhotoForCategoryBackground = photoForCategoryBackground;
        this.description = description;
        PresetsDNG_Format = presetsDNG_Format;
        PresetsBefore = presetsBefore;
        PresetsAfter = presetsAfter;
    }

    public AllPresetsModel() {

    }

    public AllPresetsModel(int id) {
        this.id = id;
    }

    public AllPresetsModel(int id, String presetCategoryName, String photoForCategoryBackground) {
        this.id = id;
        this.presetCategoryName = presetCategoryName;
        PhotoForCategoryBackground = photoForCategoryBackground;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }

    public String getPresetsDetailsname() {
        return presetsDetailsname;
    }

    public void setPresetsDetailsname(String presetsDetailsname) {
        this.presetsDetailsname = presetsDetailsname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String[] getPresetsDNG_Format() {
        return PresetsDNG_Format;
    }

    public void setPresetsDNG_Format(String[] presetsDNG_Format) {
        PresetsDNG_Format = presetsDNG_Format;
    }

    public String[] getPresetsBefore() {
        return PresetsBefore;
    }

    public void setPresetsBefore(String[] presetsBefore) {
        PresetsBefore = presetsBefore;
    }

    public String[] getPresetsAfter() {
        return PresetsAfter;
    }

    public void setPresetsAfter(String[] presetsAfter) {
        PresetsAfter = presetsAfter;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPresetCategoryName() {
        return presetCategoryName;
    }

    public void setPresetCategoryName(String presetCategoryName) {
        this.presetCategoryName = presetCategoryName;
    }

    public String getPhotoForCategoryBackground() {
        return PhotoForCategoryBackground;
    }

    public void setPhotoForCategoryBackground(String photoForCategoryBackground) {
        PhotoForCategoryBackground = photoForCategoryBackground;
    }
}
