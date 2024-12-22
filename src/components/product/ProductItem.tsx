import React from 'react';
import { ProductData } from "@/types/models";
import { Card, CardContent } from "../ui/card";
import { Star, Package } from 'lucide-react';
import { useNavigate } from 'react-router-dom';
import { getReviewsByProduct } from '@/services/review';
import { computeAverageRating } from '@/helpers';
import { Badge } from "@/components/ui/badge";

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

  const isLowStock = product.quantity <= 5;

  return (
    <Card 
      onClick={handleSelectProduct} 
      className="group hover:shadow-lg transition-all duration-300 h-full flex flex-col overflow-hidden cursor-pointer"
    >
      <div className="relative aspect-square overflow-hidden bg-gray-100">
        <img
          src={product.imageUrl}
          alt={product.title}
          className="w-full h-full object-cover group-hover:scale-105 transition-transform duration-300"
        />
        {isLowStock && (
          <Badge className="absolute top-2 right-2 bg-red-500">
            Low Stock
          </Badge>
        )}
      </div>
      
      <CardContent className="p-4 flex flex-col flex-grow">
        <div className="flex items-start justify-between gap-2">
          <h3 className="text-sm font-medium leading-tight line-clamp-2 flex-grow">
            {product.title}
          </h3>
          <div className="flex items-center text-yellow-500 whitespace-nowrap">
            <span className="font-semibold text-sm">{averageRating}</span>
            <Star size={16} fill="currentColor" className="ml-1" />
          </div>
        </div>
        
        <div className="mt-auto pt-4 flex items-center justify-between">
          <div className="flex items-baseline gap-2">
            <span className="text-lg font-bold">
              ${product.price.toFixed(2)}
            </span>
          </div>
          <div className="flex items-center text-gray-500 text-sm">
            <Package size={14} className="mr-1" />
            <span>{product.quantity}</span>
          </div>
        </div>
      </CardContent>
    </Card>
  );
};

export default ProductItem;