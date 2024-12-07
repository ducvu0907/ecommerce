import React, { useState } from 'react';
import { ReviewData } from "@/types/models";
import ReviewItem from './ReviewItem';
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from "@/components/ui/select";

interface ReviewListProps {
  reviews: ReviewData[];
}

const ReviewList: React.FC<ReviewListProps> = ({ reviews }) => {
  const [filterRating, setFilterRating] = useState<number | null>(null);
  const [sortOrder, setSortOrder] = useState<'newest' | 'highest' | 'lowest'>('newest');

  const filteredReviews = filterRating ? reviews.filter(review => review.rating === filterRating) : reviews;

  const sortedReviews = [...filteredReviews].sort((a, b) => {
    switch (sortOrder) {
      case 'newest':
        return new Date(b.createdAt).getTime() - new Date(a.createdAt).getTime();
      case 'highest':
        return b.rating - a.rating;
      case 'lowest':
        return a.rating - b.rating;
    }
  });

  const ratingDistribution = reviews.reduce((acc, review) => {
    acc[review.rating] = (acc[review.rating] || 0) + 1;
    return acc;
  }, {} as Record<number, number>);

  return (
    <div className="bg-white rounded-lg shadow-sm">
      <div className="p-4 border-b flex justify-between items-center">
        <h2 className="text-xl font-bold text-gray-800">
          Reviews ({reviews.length})
        </h2>
        
        <div className="flex items-center space-x-4">
          <Select onValueChange={(value) => setFilterRating(value ? parseInt(value) : null)}>
            <SelectTrigger className="w-[180px]">
              <SelectValue placeholder="Filter by Rating" />
            </SelectTrigger>
            <SelectContent>
              <SelectItem value="all ratings">All Ratings</SelectItem>
              {[5, 4, 3, 2, 1].map(rating => (
                <SelectItem key={rating} value={rating.toString()}>
                  {rating} Star ({ratingDistribution[rating] || 0})
                </SelectItem>
              ))}
            </SelectContent>
          </Select>

          <Select onValueChange={(value) => setSortOrder(value as 'newest' | 'highest' | 'lowest')}>
            <SelectTrigger className="w-[180px]">
              <SelectValue placeholder="Sort Reviews" />
            </SelectTrigger>
            <SelectContent>
              <SelectItem value="newest">Newest First</SelectItem>
              <SelectItem value="highest">Highest Rating</SelectItem>
              <SelectItem value="lowest">Lowest Rating</SelectItem>
            </SelectContent>
          </Select>
        </div>
      </div>

      <div>
        {sortedReviews.length > 0 ? (
          sortedReviews.map((review) => (
            <ReviewItem 
              key={review.id}
              review={review}
            />
          ))
        ) : (
          <div className="text-center py-8 text-gray-500">
            No reviews found
          </div>
        )}
      </div>

    </div>
  );
};

export default ReviewList;