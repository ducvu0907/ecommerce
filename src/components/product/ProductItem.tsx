import React from 'react';
import { ProductData } from "@/types/models";
import { Card, CardContent } from "../ui/card";
import { Computer, Star } from 'lucide-react';
import { useNavigate } from 'react-router-dom';
import { getReviewsByProduct } from '@/services/review';
import { computeAverageRating } from '@/helpers';

interface ProductItemProps {
  product: ProductData;
}

const ProductItem: React.FC<ProductItemProps> = ({ product }) => {
  const { data: reviews } = getReviewsByProduct(product.id);
  const averageRating = computeAverageRating(reviews?.result || []);
  const navigate = useNavigate();

  const handleSelectProduct = () => {
    navigate(`/products/${product.id}`);
  };

  return (
    <Card onClick={handleSelectProduct} className="relative group hover:shadow-lg transition-all duration-300 rounded-lg bg-white cursor-pointer">
      <div className="relative">
        <img
          src={product.imageUrl}
          alt="product image"
          className="w-full h-48 object-cover rounded-t-lg"
        />
      </div>
      
      <CardContent className="p-3">
        <div className="flex justify-between items-center">
          <h3 className="text-sm font-semibold line-clamp-2">{product.title}</h3>
          <div className="flex items-center text-yellow-500">
            <span className='mr-1 font-semibold'>{averageRating}</span>
            <Star size={16} fill="currentColor" />
          </div>
        </div>
        
        <div className="mt-2 flex justify-between items-center">
          <div>
            <p className="text-sm font-bold text-black">
              ${product.price.toFixed(2)}
            </p>
          </div>
          <p className="text-xs text-gray-500">
            {product.quantity} left
          </p>
        </div>
      </CardContent>
    </Card>
  );
};

export default ProductItem;