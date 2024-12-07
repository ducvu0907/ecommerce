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
    body: body
  });

  if (response.status === 500) {
    throw new Error("An error ocurred");
  }

  return response.json() as T;
};
