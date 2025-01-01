import { AuthContext } from "@/contexts/AuthContext";
import { useContext } from "react";

type RequestOptions = {
  url: string;
  method: string;
  headers?: Record<string, string>;
  body?: any;
};

export const _request = async <T>({url, method, headers, body}: RequestOptions): Promise<T> => {
  const { token } = useContext(AuthContext);

  const requestHeaders = {
    ...(headers ? headers : {"Content-Type": "application/json"}),
    ...(token && {"token": token})
  };

  const response = await fetch(url, {
    method: method,
    headers: requestHeaders,
    body: body
  });

  if (response.status === 500) {
    throw new Error("An error occurred");
  }

  return response.json() as Promise<T>;
};
