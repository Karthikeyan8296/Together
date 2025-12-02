import { colors } from "@/constants/colors";
import { getFilePath } from "@/service/ImageService";
import * as ImagePicker from "expo-image-picker";
import { Plus, X } from "lucide-react-native";
import React from "react";
import { Image, StyleSheet, TouchableOpacity, View } from "react-native";
import { ImageUploadProps } from "types";
import Typo from "./Typo";

const ImageUpload = ({
  file = null,
  onSelect,
  onClear,
  containerStyle,
  imageStyle,
  aspectRatio = [1, 1],
  allowEditing = false,
  placeholder = "",
}: ImageUploadProps) => {
  //pic image from the library
  const pickImage = async () => {
    // No permissions request is necessary for launching the image library
    const result = await ImagePicker.launchImageLibraryAsync({
      mediaTypes: "images",
      allowsEditing: allowEditing,
      aspect: aspectRatio,
      quality: 0.5,
    });
    console.log(result);

    if (result.assets && result.assets.length > 0) {
      onSelect(result.assets[0]);
    }
  };

  return (
    <View>
      {!file && (
        <TouchableOpacity
          style={[styles.inputContainer, containerStyle]}
          onPress={pickImage}
        >
          <Plus size={24} strokeWidth={2.5} color={colors.text_primary} />
          {placeholder && (
            <Typo font="Inter_semiBold" size={14}>
              {placeholder}
            </Typo>
          )}
        </TouchableOpacity>
      )}

      {file && (
        <View style={[styles.image, imageStyle]}>
          <Image
            style={{ width: "100%", height: "100%", borderRadius: 48 }}
            source={{ uri: getFilePath(file) }}
            resizeMode="cover"
          />
          <TouchableOpacity
            style={{
              position: "absolute",
              right: -5,
              top: -5,
              borderRadius: "100%",
              borderColor: colors.light_white,
              borderWidth: 2,
              padding: 10,
              backgroundColor: "#fff",
            }}
            onPress={onClear}
          >
            <X size={22} strokeWidth={2.5} color={colors.text_primary} />
          </TouchableOpacity>
        </View>
      )}
    </View>
  );
};

export default ImageUpload;

const styles = StyleSheet.create({
  inputContainer: {
    backgroundColor: "#fff",
    borderRadius: 48,
    padding: 10,
    gap: 10,
    alignItems: "center",
    justifyContent: "center",
    flexDirection: "row",
    borderWidth: 1,
    borderColor: colors.light_white,
    height: 160,
    width: 160,
  },
  image: {
    width: 160,
    height: 160,
    borderCurve: "continuous",
    borderRadius: 14,
  },
});
