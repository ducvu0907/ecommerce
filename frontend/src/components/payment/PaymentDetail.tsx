import NotFound from "@/pages/main/NotFound";
import { createPaymentMutation, getPaymentByOrderQuery } from "@/services/payment";
import { useParams } from "react-router-dom";
import { Card, CardHeader, CardTitle, CardDescription, CardContent } from "@/components/ui/card";
import { Alert, AlertDescription, AlertTitle } from "@/components/ui/alert";
import { Skeleton } from "@/components/ui/skeleton";
import { CreditCard, Calendar, Building2, AlertCircle, Receipt, Loader } from "lucide-react";
import { formatDate } from "@/helpers";
import { Button } from "@/components/ui/button";

const PaymentDetail = () => {
  const { orderId } = useParams<{ orderId: string }>();
  const { mutate: createPayment, isPending } = createPaymentMutation();

  if (!orderId) {
    return <NotFound />;
  }

  const handleCreatePayment = () => {
    createPayment({request: {orderId}});
  };

  const { data, isLoading, isError, error } = getPaymentByOrderQuery(orderId);
  const payment = data?.result;

  if (isLoading) {
    return (
      <Card className="w-full max-w-2xl mx-auto mt-8">
        <CardHeader>
          <Skeleton className="h-8 w-3/4" />
          <Skeleton className="h-4 w-1/2 mt-2" />
        </CardHeader>
        <CardContent className="space-y-4">
          {[1, 2, 3, 4].map((i) => (
            <Skeleton key={i} className="h-12 w-full" />
          ))}
        </CardContent>
      </Card>
    );
  }

  if (isError) {
    return (
      <Alert variant="destructive" className="w-full max-w-2xl mx-auto mt-8">
        <AlertCircle className="h-4 w-4" />
        <AlertTitle>Error</AlertTitle>
        <AlertDescription>
          {error?.message || "Failed to load payment details"}
        </AlertDescription>
      </Alert>
    );
  }

  if (!payment) {
    return (
      <Card className="w-full max-w-2xl mx-auto mt-8">
        <CardHeader>
          <CardTitle className="text-2xl flex items-center gap-2">
            <Receipt className="h-6 w-6" />
            Payment Details
          </CardTitle>
          <CardDescription>
            Order is not paid
          </CardDescription>
        </CardHeader>
        <CardContent className="space-y-6">
          <Button
            onClick={handleCreatePayment}
            disabled={isPending}
            className="w-full"
          >
            {isPending ? <Loader className="animate-spin" /> : "Pay Now"}
          </Button>
        </CardContent>
      </Card>
    );
  }

  return (
    <Card className="w-full max-w-2xl mx-auto mt-8">
      <CardHeader>
        <CardTitle className="text-2xl flex items-center gap-2">
          <Receipt className="h-6 w-6" />
          Payment Details
        </CardTitle>
        <CardDescription>
          Order ID: {payment.orderId}
        </CardDescription>
      </CardHeader>
      <CardContent className="space-y-6">
        <div className="flex items-center gap-3">
          <CreditCard className="h-5 w-5 text-muted-foreground" />
          <div>
            <p className="text-sm text-muted-foreground">Amount</p>
            <p className="text-lg font-medium">
              ${payment.amount}
            </p>
          </div>
        </div>

        <div className="flex items-center gap-3">
          <Calendar className="h-5 w-5 text-muted-foreground" />
          <div>
            <p className="text-sm text-muted-foreground">Payment Date</p>
            <p className="text-lg font-medium">
              {formatDate(payment.payDate)}
            </p>
          </div>
        </div>

        <div className="flex items-center gap-3">
          <Building2 className="h-5 w-5 text-muted-foreground" />
          <div>
            <p className="text-sm text-muted-foreground">Bank Code</p>
            <p className="text-lg font-medium">{payment.bankCode}</p>
          </div>
        </div>
      </CardContent>
    </Card>
  );
};

export default PaymentDetail;