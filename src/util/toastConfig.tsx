import { AlertTriangle, CheckCircle, XCircle } from "lucide-react-native";
import React from "react";
import { View } from "react-native";
import { BaseToast, ErrorToast, ToastProps } from "react-native-toast-message";

export const toastConfig = {
  success: (props: ToastProps) => (
    <BaseToast
      {...props}
      style={{ borderLeftColor: "#22c55e", paddingVertical: 10 }}
      contentContainerStyle={{
        paddingHorizontal: 15,
      }}
      text1Style={{
        fontSize: 16,
        fontWeight: "700",
      }}
      text2Style={{
        fontSize: 14,
        color: "#3A4155",
      }}
      renderLeadingIcon={() => (
        <View
          style={{
            justifyContent: "center",
            alignItems: "center",
            height: "100%",
            paddingLeft: 8,
          }}
        >
          <CheckCircle size={24} color="#22c55e" style={{ marginLeft: 8 }} />
        </View>
      )}
    />
  ),
  error: (props: ToastProps) => (
    <ErrorToast
      {...props}
      style={{ borderLeftColor: "#ef4444", paddingVertical: 10 }}
      contentContainerStyle={{ paddingHorizontal: 15 }}
      text1Style={{
        fontSize: 16,
        fontWeight: "700",
      }}
      text2Style={{
        fontSize: 14,
        color: "#3A4155",
      }}
      renderLeadingIcon={() => (
        <View
          style={{
            justifyContent: "center",
            alignItems: "center",
            height: "100%",
            paddingLeft: 8,
          }}
        >
          <XCircle size={24} color="#ef4444" style={{ marginLeft: 8 }} />
        </View>
      )}
    />
  ),
  warning: (props: ToastProps) => (
    <BaseToast
      {...props}
      style={{ borderLeftColor: "#facc15", paddingVertical: 10 }}
      contentContainerStyle={{ paddingHorizontal: 15 }}
      text1Style={{
        fontSize: 16,
        fontWeight: "700",
      }}
      text2Style={{
        fontSize: 14,
        color: "#3A4155",
      }}
      renderLeadingIcon={() => (
        <View
          style={{
            justifyContent: "center",
            alignItems: "center",
            height: "100%",
            paddingLeft: 8,
          }}
        >
          <AlertTriangle size={24} color="#facc15" style={{ marginLeft: 8 }} />
        </View>
      )}
    />
  ),
};
