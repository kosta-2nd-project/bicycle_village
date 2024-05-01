package group2.bicycle_village.common.dto;

public class FollowEntity {

	private long followSeq;
	private long follow;
	private long follower;
	
	private FollowEntity(Builder builder) {
		this.followSeq = builder.followSeq;
		this.follow = builder.follow;
		this.follower = builder.follower;
	}
	
	public static class Builder{
		private long followSeq;
		private long follow;
		private long follower;
		
		public Builder followSeq(long followSeq) {
			this.followSeq = followSeq;
			return this;
		}
		public Builder follow(long follow) {
			this.follow = follow;
			return this;
		}
		public Builder follower(long follower) {
			this.follower = follower;
			return this;
		}
		
		public FollowEntity build() {
			return new FollowEntity(this);
		}
	}

	public long getFollowSeq() {
		return followSeq;
	}

	public long getFollow() {
		return follow;
	}

	public long getFollower() {
		return follower;
	}
	

	
}
