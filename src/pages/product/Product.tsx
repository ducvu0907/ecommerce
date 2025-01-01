import { useParams } from "react-router-dom";
import { Loader2, AlertTriangle } from "lucide-react";
import { Alert, AlertDescription, AlertTitle } from "@/components/ui/alert";
import { Button } from "@/components/ui/button";

import ProductDetail from "@/components/product/ProductDetail";
import ReviewList from "@/components/review/ReviewList";
import NotFound from "../main/NotFound";

import { getProductQuery } from "@/services/product";
import { getReviewsByProductQuery } from "@/services/review";
import CreateReviewForm from "@/components/review/CreateReviewForm";
import SellerInfo from "@/components/product/SellerInfo";

const ProductLoadingState = () => (
  <div className="flex justify-center items-center w-full py-8">
    <div className="text-center">
      <Loader2 className="mx-auto h-12 w-12 animate-spin text-blue-500" />
      <p className="mt-4 text-gray-600">Loading product details...</p>
    </div>
  </div>
);

const ProductErrorState = ({ onRetry }: { onRetry: () => void }) => (
  <div className="flex justify-center items-center w-full py-8">
    <Alert variant="destructive" className="max-w-md">
      <AlertTriangle className="h-5 w-5" />
      <AlertTitle>Error Loading Product</AlertTitle>
      <AlertDescription>
        Unable to fetch product details. Please check your connection and try again.
      </AlertDescription>
      <div className="mt-4 flex space-x-2">
        <Button variant="outline" onClick={onRetry}>
          Retry
        </Button>
      </div>
    </Alert>
  </div>
);

const ReviewsSection = ({ productId }: { productId: string }) => {
  const { data: reviews, isLoading: isReviewsLoading, isError: isReviewsError } = getReviewsByProductQuery(productId);

  if (isReviewsLoading) {
    return (
      <div className="flex justify-center items-center w-full py-8">
        <div className="text-center">
          <Loader2 className="mx-auto h-12 w-12 animate-spin text-blue-500" />
          <p className="mt-4 text-gray-600">Loading reviews...</p>
        </div>
      </div>
    );
  }

  if (isReviewsError) {
    return (
      <Alert variant="destructive">
        <AlertTriangle className="h-4 w-4" />
        <AlertTitle>Error Loading Reviews</AlertTitle>
        <AlertDescription>
          Unable to fetch product reviews
          <div className="mt-2">
            <Button variant="outline">
              Retry
            </Button>
          </div>
        </AlertDescription>
      </Alert>
    );
  }


  return <ReviewList reviews={reviews?.result || []} />;
};

const Product = () => {
  const { productId } = useParams<{ productId: string }>();

  if (!productId) {
    return <NotFound />;
  }

  const { data: product, isLoading: isProductLoading, isError: isProductError, refetch: refetchProduct } = getProductQuery(productId);

  if (isProductLoading) {
    return <ProductLoadingState />;
  }

  if (isProductError) {
    return <ProductErrorState onRetry={refetchProduct} />;
  }

  if (!product?.result) {
    return <NotFound />;
  }

  return (
    <div className="container mx-auto px-4 py-8 space-y-8">
      <div>
        <ProductDetail product={product.result} />
      </div>

      <div>
        <SellerInfo sellerId={product.result.sellerId} />
      </div>

      <div>
        <CreateReviewForm productId={productId} />
      </div>

      <div>
        <ReviewsSection productId={productId} />
      </div>
    </div>
  );
};

export default Product;