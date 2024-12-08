export type routerType = {
  title: string;
  path: string;
  element: JSX.Element;
};

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
  role: string; // seller, buyer, admin
};

export type AddItemRequest = {
  token: string;
  productId: string;
  quantity: number;
};

export type UpdateItemRequest = {
  token: string;
  quantity: number;
};

export type CreateReviewRequest = {
  token: string;
  productId: string;
  rating: number;
  content: string;
};

export type CreateOrderRequest = {
  token: string;
  description: string;
  discountId: string | null;
};

export type TokenData = {
  token: string;
};

export type AddressData = {
  id: string;
  country: string;
  street: string;
  city: string;
};

export type UserData = {
  id: string;
  username: string;
  firstName: string;
  lastName: string;
  phone: string;
  addresses: AddressData[];
};

export type ProductData = {
  id: string;
  sellerId: string;
  sku: string;
  title: string;
  imageUrl: string;
  description: string;
  price: number;
  quantity: number;
};

export type CategoryData = {
  id: string;
  title: string;
  products: ProductData[];
};

export type ReviewData = {
  id: string;
  userId: string;
  productId: string;
  rating: number;
  content: string;
  createdAt: Date;
  updatedAt: Date;
};

export type AuthData = {
  userId: string;
  role: string;
};

export type OrderItemData = {
  id: string;
  productId: string;
  quantity: number;
  price: number;
  cartId: string;

};

export type OrderData = {
  id: string;
  userId: string;
  description: string;
  totalAmount: number;
  status: string; // pending, delivering, arrived
  items: OrderItemData[];
  createdAt: Date;
};

export type CartData = {
  id: string;
  userId: string;
  items: CartItemData[];
};

export type CartItemData = {
  id: string;
  productId: string;
  quantity: number;
  price: number;
  cartId: string;
};

export type DiscountData = {
  id: string;
  description: string;
  amount: number;
  percent: number;
  startDate: Date;
  endDate: Date;
  isActive: boolean;
};

// TODO: payment interface model
export type PaymentData = {
  id: string;
};
