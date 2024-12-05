export type ApiResponse<T> = {
  code: number;
  message: string;
  result?: T;
};

export type AuthRequest = {
  token: string;
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
  role: string; // seller, buyer
};

export type Token = {
  token: string;
};

export type Address = {
  id: string;
  country: string;
  street: string;
  city: string;
};

export type User = {
  id: string;
  username: string;
  firstName: string;
  lastName: string;
  phone: string;
  addresses: Address[];
};

export type Product = {
  id: string;
  sellerId: number;
  sku: string;
  title: string;
  imageUrl: string;
  description: string;
  price: number;
  quantity: number;
};

export type Category = {
  id: string;
  title: string;
  products: Product[];
};

export type Review = {
  id: string;
  userId: string;
  productId: string;
  rating: number;
  content: string;
  createdAt: Date;
  updatedAt: Date;
};

export type Auth = {
  userId: string;
  role: string;
};

export type OrderItem = {
  id: string;
  productId: string;
  quantity: number;
  price: number;
  cartId: string;

};

export type Order = {
  id: string;
  userId: string;
  description: string;
  totalAmount: number;
  status: string;
  items: OrderItem[];
};

export type Cart = {
  id: string;
  userId: string;
  items: CartItem[];
};

export type CartItem = {
  id: string;
  productId: string;
  quantity: number;
  price: number;
  cartId: string;
};

export type Discount = {
  id: string;
  description: string;
  amount: number;
  percent: number;
  startDate: Date;
  endDate: Date;
  isActive: boolean;
};

// TODO: type for payment response
export type Payment = {
  id: string;
};
