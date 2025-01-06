import { AlertCircle, ArrowLeft } from "lucide-react";
import { Button } from "@/components/ui/button";
import { Card, CardHeader, CardTitle, CardDescription, CardContent } from "@/components/ui/card";
import { useSearchParams, useNavigate } from "react-router-dom";

const PaymentFailed = () => {
  const [searchParams] = useSearchParams();
  const navigate = useNavigate();
  const orderId = searchParams.get("orderId");

  const handleGoBack = () => {
    navigate(`/orders/${orderId}`);
  };

  return (
    <div className="flex justify-center items-center min-h-screen bg-gray-100">
      <Card className="w-full max-w-md shadow-lg">
        <CardHeader className="flex flex-col items-center text-center">
          <AlertCircle className="h-12 w-12 text-red-500 mb-4" />
          <CardTitle className="text-xl font-bold">Payment Failed</CardTitle>
          <CardDescription className="text-gray-600">
            We couldn’t process your payment for order <strong>{orderId}</strong>.
          </CardDescription>
        </CardHeader>
        <CardContent className="space-y-4">
          <div className="flex flex-col gap-3">
            <Button
              variant="outline"
              className="w-full flex items-center justify-center"
              onClick={handleGoBack}
            >
              <ArrowLeft className="mr-2 h-4 w-4" />
              Back to Order
            </Button>
          </div>
        </CardContent>
      </Card>
    </div>
  );
}

export default PaymentFailed;