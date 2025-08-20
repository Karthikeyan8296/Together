import { logoutUser, setAuthFromStorage } from "@/redux/slices/authSlice";
import { AppDispatch } from "@/redux/store";
import { loadSession } from "@/util/tokenService";
import { useEffect, useState } from "react";
import { useDispatch } from "react-redux";

//To restore auth on app start
export const useRestoreAuth = () => {
  const dispatch = useDispatch<AppDispatch>();
  const [restored, setRestored] = useState(false);

  useEffect(() => {
    (async () => {
      try {
        const { user, accessToken, refreshToken } = await loadSession();
        if (user && accessToken && refreshToken) {
          dispatch(setAuthFromStorage({ user, accessToken, refreshToken }));
        } else {
          dispatch(logoutUser()).unwrap();
        }
      } finally {
        setRestored(true);
      }
    })();
  }, [dispatch]);

  return restored;
};
