import React, { useState } from 'react';
import { Card, CardContent } from "@/components/ui/card";
import { formatDate } from "@/helpers";
import { getProductQuery } from "@/services/product";
import { Skeleton } from "@/components/ui/skeleton";
import { Alert, AlertDescription, AlertTitle } from "@/components/ui/alert";
import { Button } from "@/components/ui/button";
import { Pencil } from 'lucide-react';
import UpdateProductModal from './UpdateProductModal';

interface SellerProductInfoProps {
  productId: string;
}

const SellerProductInfo: React.FC<SellerProductInfoProps> = ({ productId }) => {
  const [showUpdateModal, setShowUpdateModal] = useState<boolean>(false);
  const { data, isLoading, isError } = getProductQuery(productId);
  const product = data?.result;

  if (isLoading) {
    return (
      <Card className="w-full">
        <CardContent className="py-6 space-y-4">
          <Skeleton className="h-6 w-1/2" />
          <div className="flex gap-4">
            <Skeleton className="w-24 h-24 rounded" />
            <div className="space-y-2 flex-1">
              {Array.from({ length: 4 }).map((_, index) => (
                <div key={index} className="flex justify-between">
                  <Skeleton className="h-4 w-24" />
                  <Skeleton className="h-4 w-32" />
                </div>
              ))}
            </div>
          </div>
        </CardContent>
      </Card>
    );
  }

  if (isError) {
    return (
      <Alert variant="destructive">
        <AlertTitle>Error</AlertTitle>
        <AlertDescription>
          Failed to load product information. Please try again later.
        </AlertDescription>
      </Alert>
    );
  }

  if (!product) {
    return (
      <Alert variant="default">
        <AlertTitle>No Product Found</AlertTitle>
        <AlertDescription>
          The product you are looking for does not exist.
        </AlertDescription>
      </Alert>
    );
  }

  return (
    <Card className="w-full">
      <UpdateProductModal
        isOpen={showUpdateModal}
        onClose={() => setShowUpdateModal(false)}
        productData={product}
      />

      <CardContent className="space-y-6">
        <div className="flex gap-6">
          <img 
            src={"https://placehold.co/600x400"}
            alt={product.title}
            className="w-24 h-24 object-cover rounded mt-4"
          />
          <div className="flex-1">
            <div className='flex flex-row items-center justify-between'>
              <h2 className="text-xl font-semibold">{product.title}</h2>
              <div className="flex justify-end py-4">
                <Button
                  variant={"default"}
                  onClick={() => setShowUpdateModal(true)}
                >
                  <Pencil className="h-4 w-4" />
                  Edit
                </Button>
              </div>
            </div>

            <div className="grid grid-cols-2 gap-x-12 gap-y-2 text-sm">
              <div className="flex justify-between">
                <span className="text-muted-foreground font-semibold">SKU</span>
                <span>{product.sku}</span>
              </div>
              <div className="flex justify-between">
                <span className="text-muted-foreground font-semibold">Price</span>
                <span>${product.price}</span>
              </div>
              <div className="flex justify-between">
                <span className="text-muted-foreground font-semibold">Quantity</span>
                <span>{product.quantity} units</span>
              </div>
              <div className="flex justify-between">
                <span className="text-muted-foreground font-semibold">Category</span>
                <span>{product.category.title}</span>
              </div>
            </div>
          </div>
        </div>

        <hr />

        <div className="grid grid-cols-1 md:grid-cols-2 gap-8">
          <div className="space-y-4">
            <div className="text-sm">
              <span className="text-muted-foreground block font-semibold mb-2">Description</span>
              <p className="text-sm">{product.description}</p>
            </div>
          </div>

          <div className="space-y-4">
            <div className="text-sm">
              <span className="text-muted-foreground block font-semibold mb-2">Created</span>
              <span>{formatDate(product.createdAt)}</span>
            </div>
            <div className="text-sm">
              <span className="text-muted-foreground block font-semibold mb-2">Last Updated</span>
              <span>{formatDate(product.updatedAt)}</span>
            </div>
          </div>
        </div>
      </CardContent>
    </Card>
  );
};

export default SellerProductInfo;