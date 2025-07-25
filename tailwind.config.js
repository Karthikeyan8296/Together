/** @type {import('tailwindcss').Config} */
module.exports = {
  content: ["./index.ts", "./src/**/*.{js,jsx,ts,tsx}"],
  presets: [require("nativewind/preset")],
  theme: {
    extend: {
      fontFamily: {
        Inter_thin: ["Inter_18pt-Thin", "SpaceMono-Regular"],
        Inter_extraLight: ["Inter_18pt-ExtraLight", "SpaceMono-Regular"],
        Inter_light: ["Inter_18pt-Light", "SpaceMono-Regular"],
        Inter_regular: ["Inter_18pt-Regular", "SpaceMono-Regular"],
        Inter_medium: ["Inter_18pt-Medium", "SpaceMono-Regular"],
        Inter_semiBold: ["Inter_18pt-SemiBold", "SpaceMono-Regular"],
        Inter_bold: ["Inter_18pt-Bold", "SpaceMono-Regular"],
        Inter_extraBold: ["Inter_18pt-ExtraBold", "SpaceMono-Regular"],
        Inter_black: ["Inter_18pt-Black", "SpaceMono-Regular"],
      },
    },
  },
  plugins: [],
};
