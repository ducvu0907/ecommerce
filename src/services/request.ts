type RequestOptions = {
  url: string;
  method: string;
  headers: Record<string, string>;
  body?: any;
};

export const _request = async <T>({url, method, headers, body}: RequestOptions): Promise<T> => {
  const response = await fetch(url, {
    method: method,
    headers: headers,
    body: JSON.stringify(body)
  });

  if (!response.ok) {
    throw new Error("Network request failed");
  }

  return response.json();
};
