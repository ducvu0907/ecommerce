export type ApiResponse<T> = {
  code: number;
  message: string;
  result?: T;
};

export type LoginRequest = {
  username: string;
  password: string;
};

export type SignupRequest = {
  username: string;
  password: string;
  firstName: string;
  lastName: string;
  phone: string;
  role: string; // seller, user
};

export type Token = {
  token: string;
};

export type Address = {
  id: number;
  country: string;
  street: string;
  city: string;
};

export type User = {
  id: number;
  username: string;
  firstName: string;
  lastName: string;
  phone: string;
  addresses: Address[];
};

export type Product = {
  id: number;
  sellerId: number;
  sku: string;
  title: string;
  imageUrl: string;
  description: string;
  price: number;
  quantity: number;
};