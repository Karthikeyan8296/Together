import { useCallback, useEffect, useRef, useState } from "react";

export function useFetchData<T>(fetcher: () => Promise<T>, deps: any[] = []) {
  const [data, setData] = useState<T | null>(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);
  const alive = useRef(true);

  const run = useCallback(async () => {
    try {
      setLoading(true);
      setError(null);
      const result = await fetcher();
      if (alive.current) setData(result);
    } catch (e: any) {
      if (alive.current) setError(e?.message || "Error fetching data");
    } finally {
      if (alive.current) setLoading(false);
    }
  }, deps);

  useEffect(() => {
    alive.current = true;
    run();
    return () => {
      alive.current = false;
    };
  }, [run]);

  return { data, loading, error, refetch: run };
}
