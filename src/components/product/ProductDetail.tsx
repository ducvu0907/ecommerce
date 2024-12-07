import React, { useContext, useState } from 'react';
import { ProductData } from "@/types/models";
import { Button } from "@/components/ui/button";
import { Heart, Star, ShoppingCart, Truck, Shield } from "lucide-react";
import { Carousel, CarouselContent, CarouselItem, CarouselNext, CarouselPrevious } from "@/components/ui/carousel";
import { computeAverageRating, isOutOfStock } from '@/helpers';
import { getReviewsByProduct } from '@/services/review';
import { addItem } from '@/services/cart';
import { AuthContext } from '@/contexts/AuthContext';
import { useNavigate } from 'react-router-dom';

interface ProductDetailProps {
  product: ProductData;
}

const ProductDetail: React.FC<ProductDetailProps> = ({ product }) => {
  const { token } = useContext(AuthContext);
  const [quantity, setQuantity] = useState(1);
  const {data: reviews } = getReviewsByProduct(product.id);
  const averageRating = computeAverageRating(reviews?.result || []);
  const isDisabled = isOutOfStock(product.quantity);
  const { mutate, isPending, isError } = addItem();
  const navigate = useNavigate();

  // placeholder images
  const productImages = [
    product.imageUrl,
    product.imageUrl,
    product.imageUrl,
    product.imageUrl
  ];

  const adjustQuantity = (type: 'increase' | 'decrease') => {
    if (type === 'increase' && quantity < product.quantity) {
      setQuantity(prev => prev + 1);
    } else if (type === 'decrease' && quantity > 1) {
      setQuantity(prev => prev - 1);
    }
  };

  const handleAddToCart = async () => {
    if (!token) {
      navigate("/");
      return;
    }
    mutate({token, productId: product.id, quantity});
  };

  return (
    <div className="bg-gray-50 h-full py-8 rounded-sm">
      <div className="container mx-auto grid grid-cols-1 md:grid-cols-2 gap-8 px-4">
        <div className="bg-white p-4 rounded-lg shadow-md">
          <Carousel className="w-full">
            <CarouselContent>
                {productImages.map((image, index) => (
                  <CarouselItem key={index}>
                    <img
                      src={image}
                      alt={`Product Image ${index + 1}`}
                      className="w-full h-[500px] object-cover rounded-lg"
                    />
                  </CarouselItem>
              ))}
            </CarouselContent>
            <CarouselPrevious />
            <CarouselNext />
          </Carousel>
          
        </div>

        <div className="bg-white p-6 rounded-lg shadow-md">
          <h1 className="text-2xl font-bold mb-4">{product.title}</h1>
          
          <div className="flex items-center space-x-4 mb-4">
            <div className="flex items-center">
              {[...Array(5)].map((_, i) => (
                <Star 
                  key={i} 
                  className={`h-5 w-5 ${i < averageRating ? 'text-yellow-500 fill-yellow-500' : 'text-gray-300'}`} 
                />
              ))}
              <span className="ml-2 text-gray-600">({averageRating})</span>
            </div>
            <span className="text-gray-400">|</span>
            <span className="text-gray-600">100+ Sold</span>
          </div>

          <div className="mb-4">
            <span className="text-3xl font-bold text-red-500">
              ${product.price.toFixed(2)}
            </span>
          </div>

          <div className="mb-4 flex items-center space-x-4">
            <div className="flex items-center">
              <Truck className="h-5 w-5 mr-2 text-gray-600" />
              <span>Free Shipping</span>
            </div>
            <div className="flex items-center">
              <Shield className="h-5 w-5 mr-2 text-green-600" />
              <span>Buyer Protection</span>
            </div>
          </div>

          <div className="flex flex-row mb-4 justify-start items-center">
            <label className="block font-normal mr-4">Quantity</label>
            <div className="flex items-center space-x-4">
              <Button 
                variant="outline" 
                size="icon" 
                onClick={() => adjustQuantity('decrease')}
                disabled={quantity <= 1}
              >
                -
              </Button>
              <span className="font-bold">{quantity}</span>
              <Button 
                variant="outline" 
                size="icon" 
                onClick={() => adjustQuantity('increase')}
                disabled={quantity >= product.quantity}
              >
                +
              </Button>
              <span className="text-gray-500">
                {product.quantity} available
              </span>
            </div>
          </div>

          <div className="flex space-x-4">
            <Button onClick={handleAddToCart} className="flex-1 bg-orange-500 hover:bg-orange-600" disabled={isDisabled || isPending}>
              {isPending ? "Adding..." : <><ShoppingCart className="mr-2" /> Add to cart</>}
            </Button>
            <Button variant="outline" size="icon">
              <Heart className="text-red-500" />
            </Button>
          </div>

          <div className="mt-6 border-t pt-4">
            <h2 className="text-xl font-semibold mb-3">Description</h2>
            <p className="text-gray-700">{product.description}</p>
          </div>

        </div>
      </div>
    </div>
  );
};

export default ProductDetail;