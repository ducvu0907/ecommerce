import { CheckCircle, ArrowLeft } from "lucide-react";
import { Button } from "@/components/ui/button";
import { Card, CardHeader, CardTitle, CardDescription, CardContent } from "@/components/ui/card";
import { useSearchParams, useNavigate } from "react-router-dom";

const PaymentSucceeded = () => {
  const [searchParams] = useSearchParams();
  const navigate = useNavigate();
  const orderId = searchParams.get("orderId");

  const handleGoBack = () => {
    navigate(`/orders/${orderId}`);
  };

  return (
    <div className="flex justify-center items-center">
      <Card className="w-full max-w-md shadow-lg">
        <CardHeader className="flex flex-col items-center text-center">
          <CheckCircle className="h-12 w-12 text-green-500 mb-4" />
          <CardTitle className="text-xl font-bold">Payment Successful</CardTitle>
          <CardDescription className="text-gray-600">
            Your payment for order <strong>{orderId}</strong> was successful.
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

export default PaymentSucceeded;